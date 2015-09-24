package software.ryancook;

import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest
{
    public final static byte BISHOP_MOVE_NE = 17;
    private byte square;
    private boolean squareIsValid;

    @Test
    public void getRank() throws Exception
    {
        assertEquals(1, Square.getRank(Square.A1));
        assertEquals(1, Square.getRank(Square.H1));
        assertEquals(2, Square.getRank(Square.A2));
        assertEquals(2, Square.getRank(Square.H2));
        assertEquals(3, Square.getRank(Square.A3));
        assertEquals(3, Square.getRank(Square.H3));
        assertEquals(4, Square.getRank(Square.A4));
        assertEquals(4, Square.getRank(Square.H4));
        assertEquals(5, Square.getRank(Square.A5));
        assertEquals(5, Square.getRank(Square.H5));
        assertEquals(6, Square.getRank(Square.A6));
        assertEquals(6, Square.getRank(Square.H6));
        assertEquals(7, Square.getRank(Square.A7));
        assertEquals(7, Square.getRank(Square.H7));
        assertEquals(8, Square.getRank(Square.A8));
        assertEquals(8, Square.getRank(Square.H8));
    }

    @Test
    public void stringToByte() throws Exception
    {
        assertEquals(Square.A1, Square.getSquare("a1"));
        assertEquals(Square.B2, Square.getSquare("b2"));
        assertEquals(Square.C3, Square.getSquare("c3"));
        assertEquals(Square.D4, Square.getSquare("d4"));
        assertEquals(Square.E5, Square.getSquare("e5"));
        assertEquals(Square.F6, Square.getSquare("f6"));
        assertEquals(Square.G7, Square.getSquare("g7"));
        assertEquals(Square.H8, Square.getSquare("h8"));
    }

    @Test
    public void byteToString() throws Exception
    {
        assertEquals("a8", Square.getString(Square.A8));
        assertEquals("b7", Square.getString(Square.B7));
        assertEquals("c6", Square.getString(Square.C6));
        assertEquals("d5", Square.getString(Square.D5));
        assertEquals("e4", Square.getString(Square.E4));
        assertEquals("f3", Square.getString(Square.F3));
        assertEquals("g2", Square.getString(Square.G2));
        assertEquals("h1", Square.getString(Square.H1));
    }

    @Test
    public void bishopMovesOneSquare() throws Exception
    {
        square = (byte) (Square.F1 + BISHOP_MOVE_NE);
        squareIsValid = Square.isValid(square);
        assertTrue(squareIsValid);
    }

    @Test
    public void bishopMovesTwoSquares() throws Exception
    {
        square = (byte) (Square.F1 + (BISHOP_MOVE_NE * 2));
        squareIsValid = Square.isValid(square);
        assertTrue("Square should be valid", squareIsValid);
    }

    @Test
    public void bishopMovesOffRightSide() throws Exception
    {
        square = (byte) (Square.F1 + (BISHOP_MOVE_NE * 3));
        squareIsValid = Square.isValid(square);
        assertFalse("Square should not be valid", squareIsValid);
    }

    @Test
    public void bishopMovesOffBottomSide() throws Exception
    {
        square = (byte) (Square.F1 + (BISHOP_MOVE_NE * 3));
        squareIsValid = Square.isValid(square);
        assertFalse("Square should not be valid", squareIsValid);
    }
}