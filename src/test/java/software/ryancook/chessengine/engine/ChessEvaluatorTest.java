package software.ryancook.chessengine.engine;

import org.junit.*;
import software.ryancook.chessengine.Position;
import software.ryancook.chessengine.game.*;
import software.ryancook.gameengine.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ChessEvaluatorTest
{
    ChessRuleBook ruleBook;
    ChessGameState board;
    ChessEvaluator evaluator;
    Negamax negamax;

    @Before
    public void setUp() throws Exception
    {
        board = new ChessGameState();
        evaluator = new ChessEvaluator();
        negamax = new Negamax(evaluator);
    }

    @Test
    public void evaluateInitialPosition() throws Exception
    {
        Position.setInitialPosition(board);
        int score = evaluator.eval(board);
        assertEquals(0, score);
    }

    @Test
    public void evaluateImbalancedPosition() throws Exception
    {
        Position.setInitialPosition(board);
        board.setPiece(ChessPiece.NULL, ChessSquare.A8);
        board.setPiece(ChessPiece.NULL, ChessSquare.B8);
        board.setPiece(ChessPiece.NULL, ChessSquare.C8);
        board.setPiece(ChessPiece.NULL, ChessSquare.D1);
        board.setPiece(ChessPiece.NULL, ChessSquare.D2);
        int score = evaluator.eval(board);
        assertEquals("White should be 5 points ahead", 105, score);
    }

    @Ignore
    public void movesOrderedByCapture() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.E4);
        board.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.D6);
        board.setPiece(ChessPiece.BLACK_BISHOP, ChessSquare.F6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.D2);
        board.setPiece(ChessPiece.BLACK_QUEEN, ChessSquare.F2);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.C5);

        List<ChessMove> moves = convertMoveListToChessMoveList(ruleBook.getMoves(board));
        evaluator.sortMoves(board, moves);

        assertEquals(ChessSquare.F2, moves.get(0).getEndSquare());
        assertEquals(ChessSquare.D2, moves.get(1).getEndSquare());
        assertEquals(ChessSquare.C5, moves.get(4).getEndSquare());
    }

    @Ignore
    public void checksBeforeCaptures() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.E4);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.B5);
        board.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.D6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.D2);
        board.setPiece(ChessPiece.BLACK_QUEEN, ChessSquare.F2);

        List<ChessMove> moves = convertMoveListToChessMoveList(ruleBook.getMoves(board));
        evaluator.sortMoves(board, moves);

        assertEquals(ChessSquare.D6, moves.get(0).getEndSquare());
        assertEquals(ChessSquare.C3, moves.get(1).getEndSquare());
        assertEquals(ChessSquare.F2, moves.get(2).getEndSquare());
        assertEquals(ChessSquare.D2, moves.get(3).getEndSquare());
    }

    private List<ChessMove> convertMoveListToChessMoveList(List<Move> moves)
    {
        List<?> m = moves;
        List<ChessMove> chessMoves = (List<ChessMove>) m;
        return chessMoves;
    }

}