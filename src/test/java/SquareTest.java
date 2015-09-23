package software.ryancook;

import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest
{
    public final static byte BISHOP_MOVE_NE = 17;
    private byte square;
    private boolean squareIsValid;

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