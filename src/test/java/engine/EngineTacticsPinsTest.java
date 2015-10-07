package software.ryancook.engine;

import org.junit.*;
import software.ryancook.*;
import software.ryancook.util.*;
import static org.junit.Assert.assertEquals;

public class EngineTacticsPinsTest
{
    Board board;
    Engine engine;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
        engine = new Engine();
    }

    @Test
    public void whiteBishopPinsKingAndRook() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KING, Square.A8);
        board.setPiece(Piece.WHITE_BISHOP, Square.E1);
        board.setPiece(Piece.BLACK_KING, Square.B6);
        board.setPiece(Piece.BLACK_ROOK, Square.D4);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F2, move.getEndSquare());
    }

    @Test
    public void blackBishopPinsKingAndRook() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_KING, Square.A8);
        board.setPiece(Piece.BLACK_BISHOP, Square.E1);
        board.setPiece(Piece.WHITE_KING, Square.B6);
        board.setPiece(Piece.WHITE_ROOK, Square.D4);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F2, move.getEndSquare());
    }
}