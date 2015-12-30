package software.ryancook.chess.engine;

import org.junit.*;
import software.ryancook.gameengine.*;
import software.ryancook.chess.util.*;
import static org.junit.Assert.assertEquals;

public class EngineTacticsPinsTest
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
    public void whiteBishopPinsKingAndRook() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.A8)
            .setPiece(Piece.WHITE_BISHOP, Square.E1)
            .setPiece(Piece.BLACK_KING, Square.B6)
            .setPiece(Piece.BLACK_ROOK, Square.D4)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.F2, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackBishopPinsKingAndRook() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_KING, Square.A8)
            .setPiece(Piece.BLACK_BISHOP, Square.E1)
            .setPiece(Piece.WHITE_KING, Square.B6)
            .setPiece(Piece.WHITE_ROOK, Square.D4)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.F2, ((ChessMove) move).getEndSquare());
    }
}