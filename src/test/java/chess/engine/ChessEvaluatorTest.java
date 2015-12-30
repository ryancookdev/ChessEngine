package software.ryancook.chess.engine;

import org.junit.*;
import software.ryancook.chess.util.*;
import software.ryancook.gameengine.*;
import java.util.List;
import static org.junit.Assert.*;
import static software.ryancook.gameengine.Negamax.*;

public class ChessEvaluatorTest
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
    public void evaluateInitialPosition() throws Exception
    {
        gameState = Position.getInitialPosition();
        final int score = evaluator.eval(gameState);
        assertEquals(0, score);
    }

    @Test
    public void evaluateImbalancedPosition() throws Exception
    {
        gameState = Position.getInitialPosition().getBuilder()
            .setPiece(Piece.NULL, Square.A8)
            .setPiece(Piece.NULL, Square.B8)
            .setPiece(Piece.NULL, Square.C8)
            .setPiece(Piece.NULL, Square.D1)
            .setPiece(Piece.NULL, Square.D2)
            .build();
        final int score = evaluator.eval(gameState);
        assertEquals("White should be 5 points ahead", 105, score);
    }

    @Test
    public void evaluatePositionWithoutWhiteKing() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.BLACK_KING, Square.E8)
            .build();
        final int score = evaluator.eval(gameState);
        assertEquals(WORST_SCORE, score);
    }

    @Test
    public void evaluatePositionWithoutBlackeKing() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.E8)
            .build();
        final int score = evaluator.eval(gameState);
        assertEquals(BEST_SCORE, score);
    }

    @Test
    public void movesOrderedByCapture() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KNIGHT, Square.E4)
            .setPiece(Piece.BLACK_KNIGHT, Square.D6)
            .setPiece(Piece.BLACK_BISHOP, Square.F6)
            .setPiece(Piece.BLACK_ROOK, Square.D2)
            .setPiece(Piece.BLACK_QUEEN, Square.F2)
            .setPiece(Piece.BLACK_PAWN, Square.C5)
            .build();

        List<Move> moves = gameState.getMoves();
        moves = evaluator.sortMoves(gameState, moves);

        assertEquals(Square.F2, ((ChessMove) moves.get(0)).getEndSquare());
        assertEquals(Square.D2, ((ChessMove) moves.get(1)).getEndSquare());
        assertEquals(Square.C5, ((ChessMove) moves.get(4)).getEndSquare());
    }

    @Test
    public void checksBeforeCaptures() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KNIGHT, Square.E4)
            .setPiece(Piece.BLACK_KING, Square.B5)
            .setPiece(Piece.BLACK_KNIGHT, Square.D6)
            .setPiece(Piece.BLACK_ROOK, Square.D2)
            .setPiece(Piece.BLACK_QUEEN, Square.F2)
            .build();

        List<Move> moves = gameState.getMoves();
        moves = evaluator.sortMoves(gameState, moves);

        assertEquals(Square.D6, ((ChessMove) moves.get(0)).getEndSquare());
        assertEquals(Square.C3, ((ChessMove) moves.get(1)).getEndSquare());
        assertEquals(Square.F2, ((ChessMove) moves.get(2)).getEndSquare());
        assertEquals(Square.D2, ((ChessMove) moves.get(3)).getEndSquare());
    }
}