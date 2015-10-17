package software.ryancook.chessengine.engine;

import org.junit.*;
import software.ryancook.chessengine.*;
import software.ryancook.chessengine.game.*;
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
        table.put(new ChessGameState(), firstScore, evaluationDepth);
        table.put(new ChessGameState(), secondScore, evaluationDepth + 1);
        assertEquals(1, table.size());
    }

    @Test
    public void uniqueKeyIsInserted() throws Exception
    {
        int score = 0;
        table.put(new ChessGameState(), score, evaluationDepth);

        ChessGameState gameState = new ChessGameState();
        Position.setInitialPosition(gameState);
        gameState.playMove(new ChessMove(ChessSquare.E2, ChessSquare.E4));
        table.put(gameState, score, evaluationDepth);

        assertEquals(2, table.size());
    }

    @Test
    public void lookUpPosition() throws Exception
    {
        ChessGameState gameState = new ChessGameState();
        Position.setInitialPosition(gameState);
        gameState.playMove(new ChessMove(ChessSquare.E2, ChessSquare.E4));
        table.put(gameState, 0, evaluationDepth);
        assertTrue(table.hasPosition(gameState));
        assertFalse(table.hasPosition(new ChessGameState()));
    }

    @Test
    public void lookUpScore() throws Exception
    {
        table.put(new ChessGameState(), 123, evaluationDepth);
        assertEquals(123, table.getScore(new ChessGameState()));
    }
}