package software.ryancook.chessengine.engine;

import org.junit.*;
import software.ryancook.chessengine.game.*;
import software.ryancook.gameengine.*;
import static org.junit.Assert.*;

public class EngineTacticsForksTest
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
    public void whiteBishopForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.C8);
        board.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.G6);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.C6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.H1);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.E4, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackBishopForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.C8);
        board.setPiece(ChessPiece.BLACK_BISHOP, ChessSquare.G6);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.C6);
        board.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.H1);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.E4, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void whiteKnightForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.E8);
        board.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.D3);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.E6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.H3);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.F4, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackKnightForksKingAndRook() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.E8);
        board.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.D3);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.E6);
        board.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.H3);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.F4, ((ChessMove) move).getEndSquare());
    }
}