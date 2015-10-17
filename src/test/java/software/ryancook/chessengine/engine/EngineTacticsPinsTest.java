package software.ryancook.chessengine.engine;

import org.junit.*;
import software.ryancook.chessengine.game.*;
import software.ryancook.gameengine.*;
import static org.junit.Assert.assertEquals;

public class EngineTacticsPinsTest
{
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
    public void whiteBishopPinsKingAndRook() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.A8);
        board.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.E1);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.B6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.D4);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.F2, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackBishopPinsKingAndRook() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.A8);
        board.setPiece(ChessPiece.BLACK_BISHOP, ChessSquare.E1);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.B6);
        board.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.D4);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.F2, ((ChessMove) move).getEndSquare());
    }
}