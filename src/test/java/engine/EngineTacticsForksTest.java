package software.ryancook.engine;

import org.junit.*;
import software.ryancook.*;
import software.ryancook.util.*;
import static org.junit.Assert.*;

public class EngineTacticsForksTest
{
    Board board;
    Engine engine;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
        engine = new Engine(1500);
    }

    @Test
    public void whiteBishopForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KING, Square.C8);
        board.setPiece(Piece.WHITE_BISHOP, Square.G6);
        board.setPiece(Piece.BLACK_KING, Square.C6);
        board.setPiece(Piece.BLACK_ROOK, Square.H1);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.E4, move.getEndSquare());
    }

    @Test
    public void blackBishopForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_KING, Square.C8);
        board.setPiece(Piece.BLACK_BISHOP, Square.G6);
        board.setPiece(Piece.WHITE_KING, Square.C6);
        board.setPiece(Piece.WHITE_ROOK, Square.H1);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.E4, move.getEndSquare());
    }

    @Test
    public void whiteKnightForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KING, Square.E8);
        board.setPiece(Piece.WHITE_KNIGHT, Square.D3);
        board.setPiece(Piece.BLACK_KING, Square.E6);
        board.setPiece(Piece.BLACK_ROOK, Square.H3);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F4, move.getEndSquare());
    }

    @Test
    public void blackKnightForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_KING, Square.E8);
        board.setPiece(Piece.BLACK_KNIGHT, Square.D3);
        board.setPiece(Piece.WHITE_KING, Square.E6);
        board.setPiece(Piece.WHITE_ROOK, Square.H3);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F4, move.getEndSquare());
    }
}