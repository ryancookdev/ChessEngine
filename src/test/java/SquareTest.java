import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest
{
    public final static byte BISHOP_MOVE_NE = 17;

    @Test
    public void testIsValid() throws Exception
    {
        byte square = (byte) (Square.F1 + this.BISHOP_MOVE_NE);
        boolean squareIsValid = Square.isValid(square);
        assertTrue(squareIsValid);

        square = (byte) (Square.F1 + (this.BISHOP_MOVE_NE * 2));
        squareIsValid = Square.isValid(square);
        assertTrue("Square should be valid", squareIsValid);

        square = (byte) (Square.F1 + (this.BISHOP_MOVE_NE * 3));
        squareIsValid = Square.isValid(square);
        assertFalse("Square should not be valid", squareIsValid);

        square = (byte) (Square.F1 - this.BISHOP_MOVE_NE);
        squareIsValid = Square.isValid(square);
        assertFalse("Square should not be valid", squareIsValid);
    }
}