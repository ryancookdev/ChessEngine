import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest
{
    private Board board;
    private Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
    }

    @Test
    public void testCreateEmptyBoard() throws Exception
    {
        piece = board.getPiece(Square.A1);
        assertNull("A1 should not have a piece", piece);

        piece = board.getPiece(Square.H8);
        assertNull("H8 should not have a piece", piece);
    }

    @Test
    public void testInitialPosition() throws Exception
    {
        board.initialPosition();
        assertEquals(Piece.WHITE_ROOK, board.getPiece(Square.A1).type);
        assertEquals(Piece.WHITE_KNIGHT, board.getPiece(Square.B1).type);
        assertEquals(Piece.WHITE_BISHOP, board.getPiece(Square.C1).type);
        assertEquals(Piece.WHITE_QUEEN, board.getPiece(Square.D1).type);
        assertEquals(Piece.WHITE_PAWN, board.getPiece(Square.A2).type);
        assertEquals(Piece.WHITE_PAWN, board.getPiece(Square.B2).type);
        assertEquals(Piece.BLACK_PAWN, board.getPiece(Square.G7).type);
        assertEquals(Piece.BLACK_PAWN, board.getPiece(Square.H7).type);
        assertEquals(Piece.BLACK_KING, board.getPiece(Square.E8).type);
        assertEquals(Piece.BLACK_BISHOP, board.getPiece(Square.F8).type);
        assertEquals(Piece.BLACK_KNIGHT, board.getPiece(Square.G8).type);
        assertEquals(Piece.BLACK_ROOK, board.getPiece(Square.H8).type);
        assertNull(board.getPiece(Square.E4));
    }

    @Test
    public void testMovePiece() throws Exception
    {
        board.initialPosition();
        assertNull(board.getPiece(Square.A3));
        assertEquals(Piece.WHITE_PAWN, board.getPiece(Square.A2).type);
        assertEquals(Square.A2, board.getPiece(Square.A2).square);

        board.movePiece(new Move(Square.A2, Square.A3));
        assertNull(board.getPiece(Square.A2));
        assertEquals(Piece.WHITE_PAWN, board.getPiece(Square.A3).type);
        assertEquals(Square.A3, board.getPiece(Square.A3).square);
    }
}