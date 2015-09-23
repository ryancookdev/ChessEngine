package software.ryancook;

import software.ryancook.piece.*;

public class Position
{
    public static void setInitialPosition(Board board)
    {
        board.setActivePieces(Color.WHITE);

        board.setPiece(new Rook(Color.WHITE), Square.A1);
        board.setPiece(new Knight(Color.WHITE), Square.B1);
        board.setPiece(new Bishop(Color.WHITE), Square.C1);
        board.setPiece(new Queen(Color.WHITE), Square.D1);
        board.setPiece(new King(Color.WHITE), Square.E1);
        board.setPiece(new Bishop(Color.WHITE), Square.F1);
        board.setPiece(new Knight(Color.WHITE), Square.G1);
        board.setPiece(new Rook(Color.WHITE), Square.H1);
        board.setPiece(new Pawn(Color.WHITE), Square.A2);
        board.setPiece(new Pawn(Color.WHITE), Square.B2);
        board.setPiece(new Pawn(Color.WHITE), Square.C2);
        board.setPiece(new Pawn(Color.WHITE), Square.D2);
        board.setPiece(new Pawn(Color.WHITE), Square.E2);
        board.setPiece(new Pawn(Color.WHITE), Square.F2);
        board.setPiece(new Pawn(Color.WHITE), Square.G2);
        board.setPiece(new Pawn(Color.WHITE), Square.H2);
        board.setPiece(new Pawn(Color.BLACK), Square.A7);
        board.setPiece(new Pawn(Color.BLACK), Square.B7);
        board.setPiece(new Pawn(Color.BLACK), Square.C7);
        board.setPiece(new Pawn(Color.BLACK), Square.D7);
        board.setPiece(new Pawn(Color.BLACK), Square.E7);
        board.setPiece(new Pawn(Color.BLACK), Square.F7);
        board.setPiece(new Pawn(Color.BLACK), Square.G7);
        board.setPiece(new Pawn(Color.BLACK), Square.H7);
        board.setPiece(new Rook(Color.BLACK), Square.A8);
        board.setPiece(new Knight(Color.BLACK), Square.B8);
        board.setPiece(new Bishop(Color.BLACK), Square.C8);
        board.setPiece(new Queen(Color.BLACK), Square.D8);
        board.setPiece(new King(Color.BLACK), Square.E8);
        board.setPiece(new Bishop(Color.BLACK), Square.F8);
        board.setPiece(new Knight(Color.BLACK), Square.G8);
        board.setPiece(new Rook(Color.BLACK), Square.H8);
    }
}
