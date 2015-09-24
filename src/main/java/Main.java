package software.ryancook;

import java.util.Scanner;

public class Main
{
    public static Scanner scanner;
    public static Board board;

    public static void main(String[] args)
    {
        initialize();
        while (true) {
            String input = askUserForMove();
            lookForExitCommand(input);

            if (input.equals("show")) {
                System.out.println(board);
            } else {
                Move humanMove = parseMove(input);
                Move computerMove = getComputerMove(humanMove);
                displayComputerMove(computerMove);
            }
        }
    }

    private static void initialize()
    {
        board = new Board();
        Position.setInitialPosition(board);
        scanner = new Scanner(System.in);
        System.out.println("Your move:");
    }

    private static void displayComputerMove(Move computerMove)
    {
        System.out.println("  " + computerMove);
    }

    private static Move getComputerMove(Move humanMove)
    {
        board.movePiece(humanMove);

        Move computerMove = board.getLegalMoves().get(0);
        board.movePiece(computerMove);

        return computerMove;
    }

    private static Move parseMove(String input)
    {
        byte startSquare = Square.getSquare(input.split("-")[0]);
        byte endSquare = Square.getSquare(input.split("-")[1]);
        return new Move(startSquare, endSquare);
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
