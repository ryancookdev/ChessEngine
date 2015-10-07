package software.ryancook.generics;

import org.junit.*;
import software.ryancook.util.*;
import static org.junit.Assert.*;

public class MultiQueueTest
{
    private MultiQueue<Move> moves;

    @Before
    public void setUp() throws Exception
    {
        moves = new MultiQueue<>();
    }

    @Test
    public void myTest() throws Exception
    {
        moves.addLevel(null);
    }

    @Test
    public void singleLevel() throws Exception
    {
        setSingleLevelMoves();

        Move move = moves.next();
        Assert.assertEquals(Square.A2, move.getEndSquare());

        move = moves.next();
        assertEquals(Square.A3, move.getEndSquare());

        move = moves.next();
        assertEquals(Square.A4, move.getEndSquare());

        move = moves.next();
        assertEquals(null, move);
    }

    private void setSingleLevelMoves() {
        Square[] squares = new Square[]{Square.A2, Square.A3, Square.A4};
        moves.addLevel("Test");
        for (int i = 0; i < squares.length; i++) {
            moves.add(new Move(Square.A1, squares[i]));
        }
    }

    @Test
    public void multiLevelCrossOver() throws Exception
    {
        setMultiLevelMoves();
        moves.next();
        moves.next();

        Move lastMoveInFirstLevel = moves.next();
        assertEquals(Square.A4, lastMoveInFirstLevel.getEndSquare());

        Move firstMoveInSecondLevel = moves.next();
        assertEquals(Square.B2, firstMoveInSecondLevel.getEndSquare());
    }

    @Test
    public void removeLevelAndCrossOver() throws Exception
    {
        setMultiLevelMoves();

        Move lastMoveInFirstLevel = moves.next();
        assertEquals(Square.A2, lastMoveInFirstLevel.getEndSquare());

        moves.removeLevel();

        Move firstMoveInSecondLevel = moves.next();
        assertEquals(Square.B2, firstMoveInSecondLevel.getEndSquare());
    }

    @Test
    public void multiLevelEnd() throws Exception
    {
        setMultiLevelMoves();
        for (int i = 0; i < 8; i++) {
            moves.next();
        }

        assertTrue(moves.size() > 0);

        Move lastLevelLastMove = moves.next();
        assertEquals(Square.D1, lastLevelLastMove.getEndSquare());

        assertFalse(moves.size() > 0);

        Move nullMove = moves.next();
        assertEquals(null, nullMove);
    }

    private void setMultiLevelMoves()
    {
        Square[] levelOneSquares = new Square[]{Square.A2, Square.A3, Square.A4};
        Square[] levelTwoSquares = new Square[]{Square.B2, Square.C3, Square.D4};
        Square[] levelThreeSquares = new Square[]{Square.B1, Square.C1, Square.D1};

        moves.addLevel("Level 1");
        for (int i = 0; i < levelOneSquares.length; i++) {
            moves.add(new Move(Square.A1, levelOneSquares[i]));
        }

        moves.addLevel("Level 2");
        for (int i = 0; i < levelTwoSquares.length; i++) {
            moves.add(new Move(Square.A1, levelTwoSquares[i]));
        }

        moves.addLevel("Level 3");
        for (int i = 0; i < levelThreeSquares.length; i++) {
            moves.add(new Move(Square.A1, levelThreeSquares[i]));
        }
    }
}