package software.ryancook.engine;

import org.junit.*;
import software.ryancook.*;
import software.ryancook.util.*;
import static org.junit.Assert.*;

public class EngineMateTest
{
    Board board;
    Engine engine;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
        engine = new Engine(5000);
    }

    @Test
    public void whiteFindsBackRankMate() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.BLACK_KING, Square.B8);
        board.setPiece(Piece.WHITE_KING, Square.B6);
        board.setPiece(Piece.WHITE_ROOK, Square.H1);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.H8, move.getEndSquare());
    }

    @Test
    public void blackFindsBackRankMate() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.WHITE_KING, Square.B8);
        board.setPiece(Piece.BLACK_KING, Square.B6);
        board.setPiece(Piece.BLACK_ROOK, Square.H1);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.H8, move.getEndSquare());
    }

    @Test
    public void whiteAvoidsStalemate() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.BLACK_KING, Square.A8);
        board.setPiece(Piece.WHITE_KING, Square.A6);
        board.setPiece(Piece.WHITE_ROOK, Square.A1);
        Move move = engine.calculateBestMove(board);
        assertNotEquals(Square.B1, move.getEndSquare());
    }

    @Test
    public void blackAvoidsQueenBishopF7Mate() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A5));
        board.movePiece(new Move(Square.D1, Square.H5));
        board.movePiece(new Move(Square.B7, Square.B6));
        board.movePiece(new Move(Square.F1, Square.C4));
        Move move = engine.calculateBestMove(board);
    }
}