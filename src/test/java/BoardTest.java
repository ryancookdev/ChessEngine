package software.ryancook;

import org.junit.*;
import software.ryancook.piece.Movement;
import software.ryancook.piece.Piece;
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
    public void createEmptyBoard() throws Exception
    {
        piece = board.getPiece(Square.A1);
        assertNull("A1 should not have a piece", piece);

        piece = board.getPiece(Square.H8);
        assertNull("H8 should not have a piece", piece);
    }

    @Test
    public void initialPosition() throws Exception
    {
        board.initialPosition();
        assertEquals(Movement.WHITE_ROOK, board.getPiece(Square.A1).type);
        assertEquals(Movement.WHITE_KNIGHT, board.getPiece(Square.B1).type);
        assertEquals(Movement.WHITE_BISHOP, board.getPiece(Square.C1).type);
        assertEquals(Movement.WHITE_QUEEN, board.getPiece(Square.D1).type);
        assertEquals(Movement.WHITE_PAWN, board.getPiece(Square.A2).type);
        assertEquals(Movement.WHITE_PAWN, board.getPiece(Square.B2).type);
        assertEquals(Movement.BLACK_PAWN, board.getPiece(Square.G7).type);
        assertEquals(Movement.BLACK_PAWN, board.getPiece(Square.H7).type);
        assertEquals(Movement.BLACK_KING, board.getPiece(Square.E8).type);
        assertEquals(Movement.BLACK_BISHOP, board.getPiece(Square.F8).type);
        assertEquals(Movement.BLACK_KNIGHT, board.getPiece(Square.G8).type);
        assertEquals(Movement.BLACK_ROOK, board.getPiece(Square.H8).type);
        assertNull(board.getPiece(Square.E4));
    }

    @Test
    public void movePiece() throws Exception
    {
        board.initialPosition();
        board.movePiece(new Move(Square.A2, Square.A3));
        assertNull("A2 should be empty", board.getPiece(Square.A2));
        assertEquals("There should be a white pawn on A3", Movement.WHITE_PAWN, board.getPiece(Square.A3).type);
    }
}