package software.ryancook.generics;

import org.junit.*;
import software.ryancook.chessengine.game.*;
import static org.junit.Assert.*;

public class MultiLevelQueueTest
{
    private MultiLevelQueue<ChessMove> moves;

    @Before
    public void setUp() throws Exception
    {
        moves = new MultiLevelQueue<>();
    }

    @Test
    public void singleLevel() throws Exception
    {
        setSingleLevelMoves();

        ChessMove move = moves.next();
        Assert.assertEquals(ChessSquare.A2, move.getEndSquare());

        move = moves.next();
        assertEquals(ChessSquare.A3, move.getEndSquare());

        move = moves.next();
        assertEquals(ChessSquare.A4, move.getEndSquare());

        move = moves.next();
        assertEquals(null, move);
    }

    private void setSingleLevelMoves() {
        ChessSquare[] squares = new ChessSquare[]{ChessSquare.A2, ChessSquare.A3, ChessSquare.A4};
        moves.addLevel("Test");
        for (int i = 0; i < squares.length; i++) {
            moves.add(new ChessMove(ChessSquare.A1, squares[i]));
        }
    }

    @Test
    public void multiLevelCrossOver() throws Exception
    {
        setMultiLevelMoves();
        moves.next();
        moves.next();

        ChessMove lastMoveInFirstLevel = moves.next();
        assertEquals(ChessSquare.A4, lastMoveInFirstLevel.getEndSquare());

        ChessMove firstMoveInSecondLevel = moves.next();
        assertEquals(ChessSquare.B2, firstMoveInSecondLevel.getEndSquare());
    }

    @Test
    public void removeLevelAndCrossOver() throws Exception
    {
        setMultiLevelMoves();

        ChessMove lastMoveInFirstLevel = moves.next();
        assertEquals(ChessSquare.A2, lastMoveInFirstLevel.getEndSquare());

        moves.removeLevel();

        ChessMove firstMoveInSecondLevel = moves.next();
        assertEquals(ChessSquare.B2, firstMoveInSecondLevel.getEndSquare());
    }

    @Test
    public void multiLevelEnd() throws Exception
    {
        setMultiLevelMoves();
        for (int i = 0; i < 8; i++) {
            moves.next();
        }

        assertTrue(moves.size() > 0);

        ChessMove lastLevelLastMove = moves.next();
        assertEquals(ChessSquare.D1, lastLevelLastMove.getEndSquare());

        assertFalse(moves.size() > 0);

        ChessMove nullMove = moves.next();
        assertEquals(null, nullMove);
    }

    private void setMultiLevelMoves()
    {
        ChessSquare[] levelOneSquares = new ChessSquare[]{ChessSquare.A2, ChessSquare.A3, ChessSquare.A4};
        ChessSquare[] levelTwoSquares = new ChessSquare[]{ChessSquare.B2, ChessSquare.C3, ChessSquare.D4};
        ChessSquare[] levelThreeSquares = new ChessSquare[]{ChessSquare.B1, ChessSquare.C1, ChessSquare.D1};

        moves.addLevel("Level 1");
        for (int i = 0; i < levelOneSquares.length; i++) {
            moves.add(new ChessMove(ChessSquare.A1, levelOneSquares[i]));
        }

        moves.addLevel("Level 2");
        for (int i = 0; i < levelTwoSquares.length; i++) {
            moves.add(new ChessMove(ChessSquare.A1, levelTwoSquares[i]));
        }

        moves.addLevel("Level 3");
        for (int i = 0; i < levelThreeSquares.length; i++) {
            moves.add(new ChessMove(ChessSquare.A1, levelThreeSquares[i]));
        }
    }
}