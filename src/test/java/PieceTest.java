import org.junit.Test;
import static org.junit.Assert.*;

public class PieceTest
{
    @Test
    public void testCreatePiece() throws Exception
    {
        Board board = new Board();
        Piece piece = new Piece(Piece.WHITE_ROOK, board, Square.A1);
        assertEquals(Piece.WHITE_ROOK, piece.type);
        assertEquals(board, piece.board);
        assertEquals(Square.A1, piece.square);
    }
}
