package software.ryancook;

import org.junit.Before;
import org.junit.Test;
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
        piece = new Bishop(Movement.WHITE_ROOK, board, Square.A1);
        assertEquals(Movement.WHITE_ROOK, piece.type);
        assertEquals(Square.A1, piece.square);
    }

    @Test
    public void pieceIsOnTheBoard() throws Exception
    {
        piece = new Bishop(Movement.WHITE_ROOK, board, Square.A1);
        assertEquals(board, piece.board);
    }
}
