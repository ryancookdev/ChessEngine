package software.ryancook.engine;

import org.junit.*;
import software.ryancook.*;
import software.ryancook.util.*;

import java.util.List;

import static org.junit.Assert.*;

public class EvaluatorTest
{
    Board board;
    Engine engine;
    Evaluator evaluator;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
        engine = new Engine();
        evaluator = new Evaluator();
    }

    @Test
    public void evaluateInitialPosition() throws Exception
    {
        Position.setInitialPosition(board);
        int score = evaluator.evaluate(board);
        assertEquals(0, score);
    }

    @Test
    public void evaluateImbalancedPosition() throws Exception
    {
        Position.setInitialPosition(board);
        board.setPiece(Piece.NULL, Square.A8);
        board.setPiece(Piece.NULL, Square.B8);
        board.setPiece(Piece.NULL, Square.C8);
        board.setPiece(Piece.NULL, Square.D1);
        board.setPiece(Piece.NULL, Square.D2);
        int score = evaluator.evaluate(board);
        assertEquals("White should be 5 points ahead", 105, score);
    }

    @Test
    public void movesOrderedByCapture() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        board.setPiece(Piece.BLACK_KNIGHT, Square.D6);
        board.setPiece(Piece.BLACK_BISHOP, Square.F6);
        board.setPiece(Piece.BLACK_ROOK, Square.D2);
        board.setPiece(Piece.BLACK_QUEEN, Square.F2);
        board.setPiece(Piece.BLACK_PAWN, Square.C5);

        List<Move> moves = RuleBook.getLegalMoves(board);
        evaluator.sortMoves(board, moves);

        assertEquals(Square.F2, moves.get(0).getEndSquare());
        assertEquals(Square.D2, moves.get(1).getEndSquare());
        assertEquals(Square.C5, moves.get(4).getEndSquare());
    }

    @Test
    public void checksBeforeCaptures() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        board.setPiece(Piece.BLACK_KING, Square.B5);
        board.setPiece(Piece.BLACK_KNIGHT, Square.D6);
        board.setPiece(Piece.BLACK_ROOK, Square.D2);
        board.setPiece(Piece.BLACK_QUEEN, Square.F2);

        List<Move> moves = RuleBook.getLegalMoves(board);
        evaluator.sortMoves(board, moves);

        assertEquals(Square.D6, moves.get(0).getEndSquare());
        assertEquals(Square.C3, moves.get(1).getEndSquare());
        assertEquals(Square.F2, moves.get(2).getEndSquare());
        assertEquals(Square.D2, moves.get(3).getEndSquare());
    }

}