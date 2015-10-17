package software.ryancook.chessengine;

import software.ryancook.chessengine.game.*;

public final class Position
{
    public static void setInitialPosition(ChessGameState gameState)
    {
        gameState.setActivePieces(Color.WHITE);

        gameState.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.A1);
        gameState.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.B1);
        gameState.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.C1);
        gameState.setPiece(ChessPiece.WHITE_QUEEN, ChessSquare.D1);
        gameState.setPiece(ChessPiece.WHITE_KING, ChessSquare.E1);
        gameState.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.F1);
        gameState.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.G1);
        gameState.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.H1);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.A2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.B2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.C2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.G2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.H2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.A7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.B7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.C7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.G7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.H7);
        gameState.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.A8);
        gameState.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.B8);
        gameState.setPiece(ChessPiece.BLACK_BISHOP, ChessSquare.C8);
        gameState.setPiece(ChessPiece.BLACK_QUEEN, ChessSquare.D8);
        gameState.setPiece(ChessPiece.BLACK_KING, ChessSquare.E8);
        gameState.setPiece(ChessPiece.BLACK_BISHOP, ChessSquare.F8);
        gameState.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.G8);
        gameState.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.H8);
    }
}
