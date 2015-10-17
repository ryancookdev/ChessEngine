package software.ryancook.chessengine.engine;

import org.junit.*;
import software.ryancook.chessengine.Position;
import software.ryancook.chessengine.game.*;
import software.ryancook.gameengine.*;
import static org.junit.Assert.*;

public class EngineMateTest
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
    public void whiteFindsBackRankMate() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.B8);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.B6);
        board.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.H1);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.H8, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackFindsBackRankMate() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.B8);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.B6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.H1);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.H8, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void whiteAvoidsStalemate() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.A8);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.A6);
        board.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.A1);
        Move move = negamax.findBestMove(board);
        assertNotEquals(ChessSquare.B1, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackAvoidsQueenBishopF7Mate() throws Exception
    {
        Position.setInitialPosition(board);
        board.playMove(new ChessMove(ChessSquare.E2, ChessSquare.E4));
        board.playMove(new ChessMove(ChessSquare.A7, ChessSquare.A5));
        board.playMove(new ChessMove(ChessSquare.D1, ChessSquare.H5));
        board.playMove(new ChessMove(ChessSquare.B7, ChessSquare.B6));
        board.playMove(new ChessMove(ChessSquare.F1, ChessSquare.C4));
        Move move = negamax.findBestMove(board);
    }
}