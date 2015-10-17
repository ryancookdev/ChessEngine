package software.ryancook.chessengine;

import software.ryancook.chessengine.game.*;
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
            String input = askUserForMove();
            lookForExitCommand(input);

            if (input.equals("show")) {
                System.out.println(gameState);
            } else {
                getAndPlayHumanMove(input);
                Move computerMove = getAndPlayComputerMove();
                displayComputerMove(computerMove);
            }
        }
    }

    private static void initialize()
    {
        gameState = new ChessGameState();
        Position.setInitialPosition((ChessGameState) gameState);
        Evaluator evaluator = new ChessEvaluator();
        negamax = new Negamax(evaluator);
        scanner = new Scanner(System.in);
        System.out.println("Your move:");
    }

    private static void displayComputerMove(Move computerMove)
    {
        System.out.println("  " + computerMove + "\n");
        System.out.println(gameState);
    }

    private static Move getAndPlayComputerMove()
    {
        Move computerMove = negamax.findBestMove(gameState);
        if (!computerMove.isNull()) {
            gameState = gameState.playMove(computerMove);
        }
        return computerMove;
    }

    private static Move getAndPlayHumanMove(String input)
    {
        ChessSquare startSquare = ChessSquare.getSquare(input.split("-")[0]);
        ChessSquare endSquare = ChessSquare.getSquare(input.split("-")[1]);
        ChessMove move = new ChessMove(startSquare, endSquare);
        gameState = gameState.playMove(move);
        return move;
    }

    private static String askUserForMove()
    {
        System.out.print("> ");
        return scanner.nextLine();
    }

    private static void lookForExitCommand(String input)
    {
        if (input.equals("q") || input.equals("exit")) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }
}
