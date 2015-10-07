package software.ryancook.engine;

import org.junit.*;
import software.ryancook.*;
import software.ryancook.util.*;
import static org.junit.Assert.*;

public class EngineCaptureTest
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
    public void whiteBeginsExchanges() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KING, Square.H1);
        board.setPiece(Piece.WHITE_PAWN, Square.D4);
        board.setPiece(Piece.WHITE_PAWN, Square.F4);
        board.setPiece(Piece.BLACK_PAWN, Square.E5);
        board.setPiece(Piece.BLACK_PAWN, Square.F6);
        board.setPiece(Piece.BLACK_KING, Square.A8);
        Move move = engine.calculateBestMove(board);
        //assertEquals(1, move.getValue());
    }

    @Test
    public void blackBeginsExchanges() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.WHITE_KING, Square.H1);
        board.setPiece(Piece.WHITE_PAWN, Square.D3);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.D5);
        board.setPiece(Piece.BLACK_PAWN, Square.F5);
        board.setPiece(Piece.BLACK_KING, Square.A8);
        Move move = engine.calculateBestMove(board);
        //assertEquals(1, move.getValue());
    }

    @Ignore
    public void findBestTrade() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_BISHOP, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.C2);
        board.setPiece(Piece.BLACK_KNIGHT, Square.C6);
        board.setPiece(Piece.BLACK_ROOK, Square.H7);
        board.setPiece(Piece.BLACK_ROOK, Square.H1);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.C6, move.getEndSquare());
    }

    @Test
    public void findBestFreeCaptureAsWhite() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        board.setPiece(Piece.BLACK_KNIGHT, Square.D6);
        board.setPiece(Piece.BLACK_BISHOP, Square.F6);
        board.setPiece(Piece.BLACK_ROOK, Square.D2);
        board.setPiece(Piece.BLACK_QUEEN, Square.F2);
        board.setPiece(Piece.BLACK_PAWN, Square.C5);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F2, move.getEndSquare());
    }

    @Test
    public void findBestFreeCaptureAsBlack() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_KNIGHT, Square.E4);
        board.setPiece(Piece.WHITE_KNIGHT, Square.D6);
        board.setPiece(Piece.WHITE_BISHOP, Square.F6);
        board.setPiece(Piece.WHITE_ROOK, Square.D2);
        board.setPiece(Piece.WHITE_QUEEN, Square.F2);
        board.setPiece(Piece.WHITE_PAWN, Square.C5);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F2, move.getEndSquare());
    }

    @Ignore
    public void mustBlockEndgameMate() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.WHITE_KING, Square.A8);
        board.setPiece(Piece.WHITE_ROOK, Square.F3);
        board.setPiece(Piece.BLACK_PAWN, Square.F4);
        board.setPiece(Piece.BLACK_KING, Square.H1);
        board.setPiece(Piece.BLACK_ROOK, Square.B1);
        board.setPiece(Piece.BLACK_ROOK, Square.H2);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.A3, move.getEndSquare());
    }

    @Ignore
    public void BlackDoesNotHangKing() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A6));
        board.movePiece(new Move(Square.D1, Square.H5));
        board.movePiece(new Move(Square.B7, Square.B6));
        board.movePiece(new Move(Square.F1, Square.C4));
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F6, move.getEndSquare());
    }
}