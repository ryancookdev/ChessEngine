package software.ryancook.chess.engine;

import org.junit.*;
import software.ryancook.gameengine.*;
import software.ryancook.chess.util.*;
import static org.junit.Assert.*;

public class EngineCaptureTest
{
    ChessEvaluator evaluator;
    Negamax negamax;

    @Before
    public void setUp() throws Exception
    {
        evaluator = new ChessEvaluator();
        negamax = new Negamax(evaluator);
        negamax.setMaxTime(1000);
    }

    @Test
    public void whiteBeginsExchanges() throws Exception
    {
        ChessGameState gameState = new ChessGameState.Builder(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.H1)
            .setPiece(Piece.WHITE_PAWN, Square.D4)
            .setPiece(Piece.WHITE_PAWN, Square.F4)
            .setPiece(Piece.BLACK_PAWN, Square.E5)
            .setPiece(Piece.BLACK_PAWN, Square.F6)
            .setPiece(Piece.BLACK_KING, Square.A8)
            .build();
        final Move move = negamax.findBestMove(gameState);
    }

    @Test
    public void blackBeginsExchanges() throws Exception
    {
        ChessGameState gameState = new ChessGameState.Builder(Color.BLACK)
            .setPiece(Piece.WHITE_KING, Square.H1)
            .setPiece(Piece.WHITE_PAWN, Square.D3)
            .setPiece(Piece.WHITE_PAWN, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.D5)
            .setPiece(Piece.BLACK_PAWN, Square.F5)
            .setPiece(Piece.BLACK_KING, Square.A8)
            .build();
        final Move move = negamax.findBestMove(gameState);
    }

    @Test
    public void findBestFreeCaptureAsWhite() throws Exception
    {
        ChessGameState gameState = new ChessGameState.Builder(Color.WHITE)
            .setPiece(Piece.WHITE_KNIGHT, Square.E4)
            .setPiece(Piece.BLACK_KNIGHT, Square.D6)
            .setPiece(Piece.BLACK_BISHOP, Square.F6)
            .setPiece(Piece.BLACK_ROOK, Square.D2)
            .setPiece(Piece.BLACK_QUEEN, Square.F2)
            .setPiece(Piece.BLACK_PAWN, Square.C5)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.F2, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void findBestFreeCaptureAsBlack() throws Exception
    {
        ChessGameState gameState = new ChessGameState.Builder(Color.BLACK)
            .setPiece(Piece.BLACK_KNIGHT, Square.E4)
            .setPiece(Piece.WHITE_KNIGHT, Square.D6)
            .setPiece(Piece.WHITE_BISHOP, Square.F6)
            .setPiece(Piece.WHITE_ROOK, Square.D2)
            .setPiece(Piece.WHITE_QUEEN, Square.F2)
            .setPiece(Piece.WHITE_PAWN, Square.C5)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.F2, ((ChessMove) move).getEndSquare());
    }
}