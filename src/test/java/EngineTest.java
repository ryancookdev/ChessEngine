package software.ryancook;

import org.junit.*;
import java.util.List;
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
    public void movesOrderedByCapture() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        board.setPiece(Piece.BLACK_KNIGHT, Square.D6);
        board.setPiece(Piece.BLACK_BISHOP, Square.F6);
        board.setPiece(Piece.BLACK_ROOK, Square.D2);
        board.setPiece(Piece.BLACK_QUEEN, Square.F2);
        board.setPiece(Piece.BLACK_PAWN, Square.C5);
        List<Move> moves = engine.getSortedMoves(board);
        assertEquals(Square.F2, moves.get(0).endSquare);
        assertEquals(Square.D2, moves.get(1).endSquare);
        assertEquals(Square.C5, moves.get(4).endSquare);
    }

    @Ignore
    public void startWithPlyGreaterThanZero() throws Exception
    {
        Position.setInitialPosition(board);
        board.movePiece(new Move(Square.E2, Square.E4));
        board.movePiece(new Move(Square.A7, Square.A6));
        board.movePiece(new Move(Square.F1, Square.A6));
        Move move = engine.calculateBestMove(board);
        assertEquals(Square.A6, move.endSquare);
    }

    @Ignore
    public void maxDepthAndNodes() throws Exception
    {
        engine = new Engine(7, 150000);

        Position.setInitialPosition(board);

        long startTime = System.currentTimeMillis();
        Move move = engine.calculateBestMove(board);
        long totalTime = System.currentTimeMillis() - startTime;

        System.out.println("Time: " + totalTime + " ms");
        System.out.println("Total nodes: " + engine.getTotalNodes());
        System.out.println("Move: " + move);

        assertNotNull(move);
    }
}