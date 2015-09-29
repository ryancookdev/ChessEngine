package software.ryancook;

import org.junit.*;

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
        byte piece = board.getPiece(Square.A1);
        assertEquals("A1 should not have a piece", 0, piece);

        piece = board.getPiece(Square.H8);
        assertEquals("H8 should not have a piece", 0, piece);
    }

    @Test
    public void initialPosition() throws Exception
    {
        Position.setInitialPosition(board);
        assertEquals(Piece.WHITE_ROOK, board.getPiece(Square.A1));
        assertEquals(Piece.WHITE_KNIGHT, board.getPiece(Square.B1));
        assertEquals(Piece.WHITE_BISHOP, board.getPiece(Square.C1));
        assertEquals(Piece.WHITE_QUEEN, board.getPiece(Square.D1));
        assertEquals(Piece.WHITE_PAWN, board.getPiece(Square.A2));
        assertEquals(Piece.WHITE_PAWN, board.getPiece(Square.B2));
        assertEquals(Piece.BLACK_PAWN, board.getPiece(Square.G7));
        assertEquals(Piece.BLACK_PAWN, board.getPiece(Square.H7));
        assertEquals(Piece.BLACK_KING, board.getPiece(Square.E8));
        assertEquals(Piece.BLACK_BISHOP, board.getPiece(Square.F8));
        assertEquals(Piece.BLACK_KNIGHT, board.getPiece(Square.G8));
        assertEquals(Piece.BLACK_ROOK, board.getPiece(Square.H8));
        assertEquals(0, board.getPiece(Square.E4));
    }

    @Test
    public void countPieces() throws Exception
    {
        Position.setInitialPosition(board);
        assertEquals(16, board.getTotalWhitePieces());
    }

    @Test
    public void copiedBoardHasNewBoardReference() throws Exception
    {
        Position.setInitialPosition(board);
        Board newBoard = new Board(board);
        board.movePiece(new Move(Square.A2, Square.A3));
        assertEquals("There should still be a white pawn on A2", Piece.WHITE_PAWN, newBoard.getPiece(Square.A2));
    }

    @Test
    public void copiedBoardHasNewPieceListReference() throws Exception
    {
        Position.setInitialPosition(board);
        Board newBoard = new Board(board);
        board.setPiece((byte) 0, Square.A2);
        assertEquals(16, newBoard.getTotalWhitePieces());
        assertEquals(15, board.getTotalWhitePieces());
    }

    @Test
    public void colorToMove() throws Exception
    {
        Position.setInitialPosition(board);
        assertEquals(Color.WHITE, board.getColorToMove());
    }

    @Test
    public void movePiece() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.A2, Square.A3));
        assertEquals("A2 should be empty", 0, board.getPiece(Square.A2));
        assertEquals("There should be a white pawn on A3", Piece.WHITE_PAWN, board.getPiece(Square.A3));
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