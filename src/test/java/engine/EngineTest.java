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
        engine = new Engine(1000000);
        //engine = new Engine(5000);
        evaluator = new Evaluator();
    }

    @Test
    public void myTest() throws Exception
    {
        /*Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A6));
        board.movePiece(new Move(Square.D2, Square.D4));
        board.movePiece(new Move(Square.B7, Square.B5));
        board.movePiece(new Move(Square.C2, Square.C4));
        Move move = engine.calculateBestMove(board);
        System.out.println("Play: " + move);*/
    }
}