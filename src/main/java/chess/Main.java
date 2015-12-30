package software.ryancook.chess;

import software.ryancook.chess.engine.*;
import software.ryancook.chess.util.*;
import software.ryancook.gameengine.*;
import java.util.Scanner;

public class Main
{
    public static Scanner scanner;
    public static GameState gameState;
    public static Negamax negamax;

    public static void main(String[] args)
    {
        initialize();
        while (true) {
            final String input = askUserForMove();
            lookForExitCommand(input);

            if (input.equals("show")) {
                System.out.println(gameState);
            } else {
                getAndPlayHumanMove(input);
                final Move computerMove = getAndPlayComputerMove();
                displayComputerMove(computerMove);
            }
        }
    }

    private static void initialize()
    {
        gameState = Position.getInitialPosition();
        final Evaluator evaluator = new ChessEvaluator();
        negamax = new Negamax(evaluator);
        negamax.setMaxTime(10000);
        scanner = new Scanner(System.in);
        System.out.println("Your move:");
    }

    private static void displayComputerMove(final Move computerMove)
    {
        System.out.println("  " + computerMove + "\n");
        System.out.println(gameState);
    }

    private static Move getAndPlayComputerMove()
    {
        final Move computerMove = negamax.findBestMove(gameState);
        if (!computerMove.isNull()) {
            gameState = gameState.playMove(computerMove);
        }
        return computerMove;
    }

    private static Move getAndPlayHumanMove(final String input)
    {
        final Square startSquare = Square.getSquare(input.split("-")[0]);
        final Square endSquare = Square.getSquare(input.split("-")[1]);
        final ChessMove move = new ChessMove(startSquare, endSquare);
        gameState = gameState.playMove(move);
        return move;
    }

    private static String askUserForMove()
    {
        System.out.print("> ");
        return scanner.nextLine();
    }

    private static void lookForExitCommand(final String input)
    {
        if (input.equals("q") || input.equals("exit")) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }
}
