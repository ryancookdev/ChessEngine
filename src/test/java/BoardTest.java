package software.ryancook;

import org.junit.*;
import software.ryancook.piece.*;
import java.util.List;
import static org.junit.Assert.*;

public class BoardTest
{
    private Board board;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
    }

    @Test
    public void createEmptyBoard() throws Exception
    {
        Piece piece = board.getPiece(Square.A1);
        assertNull("A1 should not have a piece", piece);

        piece = board.getPiece(Square.H8);
        assertNull("H8 should not have a piece", piece);
    }

    @Test
    public void initialPosition() throws Exception
    {
        Position.setInitialPosition(board);
        assertEquals(Movement.WHITE_ROOK, board.getPiece(Square.A1).getType());
        assertEquals(Movement.WHITE_KNIGHT, board.getPiece(Square.B1).getType());
        assertEquals(Movement.WHITE_BISHOP, board.getPiece(Square.C1).getType());
        assertEquals(Movement.WHITE_QUEEN, board.getPiece(Square.D1).getType());
        assertEquals(Movement.WHITE_PAWN, board.getPiece(Square.A2).getType());
        assertEquals(Movement.WHITE_PAWN, board.getPiece(Square.B2).getType());
        assertEquals(Movement.BLACK_PAWN, board.getPiece(Square.G7).getType());
        assertEquals(Movement.BLACK_PAWN, board.getPiece(Square.H7).getType());
        assertEquals(Movement.BLACK_KING, board.getPiece(Square.E8).getType());
        assertEquals(Movement.BLACK_BISHOP, board.getPiece(Square.F8).getType());
        assertEquals(Movement.BLACK_KNIGHT, board.getPiece(Square.G8).getType());
        assertEquals(Movement.BLACK_ROOK, board.getPiece(Square.H8).getType());
        assertNull(board.getPiece(Square.E4));
    }

    @Test
    public void movePiece() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.A2, Square.A3));
        assertNull("A2 should be empty", board.getPiece(Square.A2));
        assertEquals("There should be a white pawn on A3", Movement.WHITE_PAWN, board.getPiece(Square.A3).getType());
    }

    @Test
    public void countPiecesOnBoard() throws Exception
    {
        Position.setInitialPosition(board);
        assertEquals("There should be 16 black pieces", 16, board.getTotalBlackPieces());
        assertEquals("There should be 16 white pieces", 16, board.getTotalWhitePieces());
    }

    @Test
    public void legalMovesForInitialPosition() throws Exception
    {
        Position.setInitialPosition(board);
        List<Move> moves = board.getLegalMoves();
        assertEquals(20, moves.size());
    }
}