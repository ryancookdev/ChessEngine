package software.ryancook.engine;

import org.junit.*;
import software.ryancook.*;
import software.ryancook.util.*;
import static org.junit.Assert.*;

public class EngineTest
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

    @Ignore
    public void startWithPlyGreaterThanZero() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A6));
        board.movePiece(new Move(Square.F1, Square.A6));
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.A6, move.getEndSquare());
    }
}