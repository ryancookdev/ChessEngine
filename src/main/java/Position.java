package software.ryancook;

import software.ryancook.util.*;

public final class Position
{
    public static void setInitialPosition(Board board)
    {
        board.setActivePieces(Color.WHITE);

        board.setPiece(Piece.WHITE_ROOK, Square.A1);
        board.setPiece(Piece.WHITE_KNIGHT, Square.B1);
        board.setPiece(Piece.WHITE_BISHOP, Square.C1);
        board.setPiece(Piece.WHITE_QUEEN, Square.D1);
        board.setPiece(Piece.WHITE_KING, Square.E1);
        board.setPiece(Piece.WHITE_BISHOP, Square.F1);
        board.setPiece(Piece.WHITE_KNIGHT, Square.G1);
        board.setPiece(Piece.WHITE_ROOK, Square.H1);
        board.setPiece(Piece.WHITE_PAWN, Square.A2);
        board.setPiece(Piece.WHITE_PAWN, Square.B2);
        board.setPiece(Piece.WHITE_PAWN, Square.C2);
        board.setPiece(Piece.WHITE_PAWN, Square.D2);
        board.setPiece(Piece.WHITE_PAWN, Square.E2);
        board.setPiece(Piece.WHITE_PAWN, Square.F2);
        board.setPiece(Piece.WHITE_PAWN, Square.G2);
        board.setPiece(Piece.WHITE_PAWN, Square.H2);
        board.setPiece(Piece.BLACK_PAWN, Square.A7);
        board.setPiece(Piece.BLACK_PAWN, Square.B7);
        board.setPiece(Piece.BLACK_PAWN, Square.C7);
        board.setPiece(Piece.BLACK_PAWN, Square.D7);
        board.setPiece(Piece.BLACK_PAWN, Square.E7);
        board.setPiece(Piece.BLACK_PAWN, Square.F7);
        board.setPiece(Piece.BLACK_PAWN, Square.G7);
        board.setPiece(Piece.BLACK_PAWN, Square.H7);
        board.setPiece(Piece.BLACK_ROOK, Square.A8);
        board.setPiece(Piece.BLACK_KNIGHT, Square.B8);
        board.setPiece(Piece.BLACK_BISHOP, Square.C8);
        board.setPiece(Piece.BLACK_QUEEN, Square.D8);
        board.setPiece(Piece.BLACK_KING, Square.E8);
        board.setPiece(Piece.BLACK_BISHOP, Square.F8);
        board.setPiece(Piece.BLACK_KNIGHT, Square.G8);
        board.setPiece(Piece.BLACK_ROOK, Square.H8);
    }
}
