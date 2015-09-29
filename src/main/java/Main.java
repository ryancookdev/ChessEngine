package software.ryancook;

import java.util.Scanner;

public class Main
{
    public static Scanner scanner;
    public static Board board;
    public static Engine engine;

    public static void main(String[] args)
    {
        initialize();
        while (true) {
            String input = askUserForMove();
            lookForExitCommand(input);

            if (input.equals("show")) {
                System.out.println(board);
                System.out.println("Score: " + engine.evaluatePosition(board));
            } else {
                getAndPlayHumanMove(input);
                Move computerMove = getAndPlayComputerMove();
                displayComputerMove(computerMove);
            }
        }
    }

    private static void initialize()
    {
        board = new Board();
        Position.setInitialPosition(board);
        engine = new Engine();
        engine.setMaxNodes(10000);
        scanner = new Scanner(System.in);
        System.out.println("Your move:");
    }

    private static void displayComputerMove(Move computerMove)
    {
        System.out.println("  " + computerMove + "\n");
        System.out.println(board);
    }

    private static Move getAndPlayComputerMove()
    {
        Move computerMove = engine.calculateBestMove(board);
        board.movePiece(computerMove);
        return computerMove;
    }

    private static Move getAndPlayHumanMove(String input)
    {
        byte startSquare = Square.getSquare(input.split("-")[0]);
        byte endSquare = Square.getSquare(input.split("-")[1]);
        Move move = new Move(startSquare, endSquare);
        board.movePiece(move);
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
