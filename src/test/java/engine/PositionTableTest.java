package software.ryancook.engine;

import org.junit.*;
import software.ryancook.*;
import software.ryancook.util.*;
import static org.junit.Assert.*;

public class PositionTableTest
{
    PositionTable table;
    int evaluationDepth = 1;

    @Before
    public void setUp() throws Exception
    {
        table = new PositionTable();
    }

    @Test
    public void duplicateKeyIsOverwritten() throws Exception
    {
        int firstScore = -100;
        int secondScore = 100;
        table.put(new Board(), firstScore, evaluationDepth);
        table.put(new Board(), secondScore, evaluationDepth + 1);
        assertEquals(1, table.size());
    }

    @Test
    public void uniqueKeyIsInserted() throws Exception
    {
        int score = 0;
        table.put(new Board(), score, evaluationDepth);

        Board board = new Board();
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        table.put(board, score, evaluationDepth);

        assertEquals(2, table.size());
    }

    @Test
    public void lookUpPosition() throws Exception
    {
        Board board = new Board();
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        table.put(board, 0, evaluationDepth);
        assertTrue(table.hasPosition(board));
        assertFalse(table.hasPosition(new Board()));
    }

    @Test
    public void lookUpScore() throws Exception
    {
        table.put(new Board(), 123, evaluationDepth);
        assertEquals(123, table.getScore(new Board()));
    }
}