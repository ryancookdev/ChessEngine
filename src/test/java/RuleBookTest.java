package software.ryancook;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class RuleBookTest
{
    @Test
    public void getLegalMoves() throws Exception
    {
        Board board = new Board();
        board.initialPosition();
        ArrayList<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals("Initial position should have 20 legal moves", 20, moves.size());
    }
}
