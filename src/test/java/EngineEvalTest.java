package software.ryancook;

import org.junit.*;
import static org.junit.Assert.*;

public class EngineEvalTest
{
    Board board;
    Engine engine;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
        engine = new Engine();
    }

    @Test
    public void evaluateInitialPosition() throws Exception
    {
        Position.setInitialPosition(board);
        int score = engine.evaluatePosition(board);
        assertEquals(0, score);
    }

    @Test
    public void evaluateAfterSomeMoves() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A6));
        assertEquals(16, board.getWhitePieces().size());
    }

    @Test
    public void evaluateImbalancedPosition() throws Exception
    {
        Position.setInitialPosition(board);
        board.setPiece((byte) 0, Square.A8);
        board.setPiece((byte) 0, Square.B8);
        board.setPiece((byte) 0, Square.C8);
        board.setPiece((byte) 0, Square.D1);
        board.setPiece((byte) 0, Square.D2);
        int score = engine.evaluatePosition(board);
        assertEquals("White should be 1 point ahead", -1, score);
    }
}