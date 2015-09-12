import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class RuleBookTest
{
    @Test
    public void testGetLegalMoves() throws Exception
    {
        Board board = new Board();
        board.initialPosition();
        ArrayList<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals("Initial position should have 20 legal moves", 20, moves.size());
    }
}
