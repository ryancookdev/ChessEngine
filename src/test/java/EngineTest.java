package software.ryancook;

import org.junit.*;
import static org.junit.Assert.*;

public class EngineTest
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
    public void evaluateInitialPosition() throws Exception
    {
        Position.setInitialPosition(board);
        int score = engine.evaluatePosition(board);
        assertEquals(0, score);
    }

    @Test
    public void evaluateAfterSomeMoves() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A6));
        assertEquals(16, board.getWhitePieces().size());
    }

    @Test
    public void evaluateImbalancedPosition() throws Exception
    {
        Position.setInitialPosition(board);
        board.setPiece((byte) 0, Square.A8);
        board.setPiece((byte) 0, Square.B8);
        board.setPiece((byte) 0, Square.C8);
        board.setPiece((byte) 0, Square.D1);
        board.setPiece((byte) 0, Square.D2);
        int score = engine.evaluatePosition(board);
        assertEquals("White should be 1 point ahead", 1, score);
    }

    @Ignore
    public void findBestFreeCaptureAsWhite() throws Exception
    {
        engine.setMaxNodes(9000);
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        board.setPiece(Piece.BLACK_KNIGHT, Square.D6);
        board.setPiece(Piece.BLACK_BISHOP, Square.F6);
        board.setPiece(Piece.BLACK_ROOK, Square.D2);
        board.setPiece(Piece.BLACK_QUEEN, Square.F2);
        board.setPiece(Piece.BLACK_PAWN, Square.C5);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F2, move.endSquare);
    }

    @Ignore
    public void findBestFreeCaptureAsBlack() throws Exception
    {
        engine.setMaxNodes(9000);
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_KNIGHT, Square.E4);
        board.setPiece(Piece.WHITE_KNIGHT, Square.D6);
        board.setPiece(Piece.WHITE_BISHOP, Square.F6);
        board.setPiece(Piece.WHITE_ROOK, Square.D2);
        board.setPiece(Piece.WHITE_QUEEN, Square.F2);
        board.setPiece(Piece.WHITE_PAWN, Square.C5);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.F2, move.endSquare);
    }

    @Ignore
    public void findBestTrade() throws Exception
    {
        engine.setMaxNodes(9000);
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_BISHOP, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.C2);
        board.setPiece(Piece.BLACK_KNIGHT, Square.C6);
        board.setPiece(Piece.BLACK_ROOK, Square.H7);
        board.setPiece(Piece.BLACK_ROOK, Square.H1);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.C6, move.endSquare);
    }

    @Ignore
    public void startWithPlyGreaterThanZero() throws Exception
    {
        engine.setMaxNodes(9000);
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A6));
        board.movePiece(new Move(Square.F1, Square.A6));
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.A6, move.endSquare);
    }

    @Ignore
    public void mustBlockEndgameMate() throws Exception
    {
        engine.setMaxNodes(9000);
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.WHITE_KING, Square.A8);
        board.setPiece(Piece.WHITE_ROOK, Square.F3);
        board.setPiece(Piece.BLACK_PAWN, Square.F4);
        board.setPiece(Piece.BLACK_KING, Square.H1);
        board.setPiece(Piece.BLACK_ROOK, Square.B1);
        board.setPiece(Piece.BLACK_ROOK, Square.H2);
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.A3, move.endSquare);
    }

    @Ignore
    public void mustBlockOpeningMate() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A5));
        board.movePiece(new Move(Square.F1, Square.C4));
        /*board.movePiece(new Move(Square.B7, Square.B6));
        board.movePiece(new Move(Square.D1, Square.F3));*/

        Move move = engine.calculateBestMove(board);
        System.out.println(move + " (" + move.value + ")");
    }

    @Test
    public void mustTerminateCalculation() throws Exception
    {
        Position.setInitialPosition(board);
        board.setPiece((byte) 0, Square.H2);
        board.setPiece((byte) 0, Square.A7);
        board.movePiece(new Move(Square.H1, Square.H7));
        Move move = engine.calculateBestMove(board);
        System.out.println(move + " (" + move.value + ")");
    }

    @Ignore
    public void whiteBeginsExchanges() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.D4);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.F4);
        board.setPiece(Piece.BLACK_PAWN, Square.E5);
        board.setPiece(Piece.BLACK_PAWN, Square.F6);
        Move move = engine.calculateBestMove(board);
        assertEquals(2, move.value);
    }

    @Ignore
    public void blackBeginsExchanges() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.WHITE_PAWN, Square.D3);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.D5);
        board.setPiece(Piece.BLACK_PAWN, Square.E5);
        board.setPiece(Piece.BLACK_PAWN, Square.F5);
        Move move = engine.calculateBestMove(board);
        assertEquals(-2, move.value);
    }

    @Ignore
    public void maxDepthAndNodes() throws Exception
    {
        engine.setMaxNodes(9000);

        Position.setInitialPosition(board);

        long startTime = System.currentTimeMillis();
        Move move = engine.calculateBestMove(board);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
        System.out.println("Total nodes: " + engine.getTotalNodes());
        System.out.println("Move: " + move);

        assertNotNull(move);
    }
}