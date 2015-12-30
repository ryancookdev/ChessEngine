package software.ryancook.chess.engine;

import org.junit.*;
import software.ryancook.gameengine.*;
import software.ryancook.chess.util.*;
import static org.junit.Assert.*;

public class EngineTacticsForksTest
{
    ChessGameState gameState;
    ChessEvaluator evaluator;
    Negamax negamax;

    @Before
    public void setUp() throws Exception
    {
        gameState = new ChessGameState.Builder(Color.WHITE).build();
        evaluator = new ChessEvaluator();
        negamax = new Negamax(evaluator);
    }

    @Test
    public void whiteBishopForksKingAndRook() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.C8)
            .setPiece(Piece.WHITE_BISHOP, Square.G6)
            .setPiece(Piece.BLACK_KING, Square.C6)
            .setPiece(Piece.BLACK_ROOK, Square.H1)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.E4, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackBishopForksKingAndRook() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_KING, Square.C8)
            .setPiece(Piece.BLACK_BISHOP, Square.G6)
            .setPiece(Piece.WHITE_KING, Square.C6)
            .setPiece(Piece.WHITE_ROOK, Square.H1)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.E4, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void whiteKnightForksKingAndRook() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.E8)
            .setPiece(Piece.WHITE_KNIGHT, Square.D3)
            .setPiece(Piece.BLACK_KING, Square.E6)
            .setPiece(Piece.BLACK_ROOK, Square.H3)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.F4, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackKnightForksKingAndRook() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_KING, Square.E8)
            .setPiece(Piece.BLACK_KNIGHT, Square.D3)
            .setPiece(Piece.WHITE_KING, Square.E6)
            .setPiece(Piece.WHITE_ROOK, Square.H3)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.F4, ((ChessMove) move).getEndSquare());
    }
}