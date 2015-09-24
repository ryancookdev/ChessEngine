package software.ryancook;

import org.junit.*;
import software.ryancook.piece.*;
import static org.junit.Assert.*;

public class PieceTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
    }

    @Test
    public void createPiece() throws Exception
    {
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.A1);
        assertEquals(Movement.WHITE_ROOK, piece.getType());
        assertEquals(Square.A1, piece.getSquare());
    }

    @Test
    public void pieceIsOnTheBoard() throws Exception
    {
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.A1);
        assertEquals(board, piece.getBoard());
    }
}
