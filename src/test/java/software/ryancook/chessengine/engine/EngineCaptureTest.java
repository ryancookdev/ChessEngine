package software.ryancook.chessengine.engine;

import org.junit.*;
import software.ryancook.chessengine.Position;
import software.ryancook.chessengine.game.*;
import software.ryancook.gameengine.*;
import static org.junit.Assert.*;

public class EngineCaptureTest
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

    @Ignore
    public void whiteBeginsExchanges() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.H1);
        board.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D4);
        board.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F4);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E5);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F6);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.A8);
        Move move = negamax.findBestMove(board);
    }

    @Ignore
    public void blackBeginsExchanges() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.H1);
        board.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D3);
        board.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E4);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D5);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F5);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.A8);
        Move move = negamax.findBestMove(board);
    }

    @Test
    public void findBestTrade() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.E4);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.C2);
        board.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.C6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.H7);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.H1);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.C6, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void findBestFreeCaptureAsWhite() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.E4);
        board.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.D6);
        board.setPiece(ChessPiece.BLACK_BISHOP, ChessSquare.F6);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.D2);
        board.setPiece(ChessPiece.BLACK_QUEEN, ChessSquare.F2);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.C5);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.F2, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void findBestFreeCaptureAsBlack() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(ChessPiece.BLACK_KNIGHT, ChessSquare.E4);
        board.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.D6);
        board.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.F6);
        board.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.D2);
        board.setPiece(ChessPiece.WHITE_QUEEN, ChessSquare.F2);
        board.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.C5);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.F2, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void mustBlockEndgameMate() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(ChessPiece.WHITE_KING, ChessSquare.A8);
        board.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.F3);
        board.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F4);
        board.setPiece(ChessPiece.BLACK_KING, ChessSquare.H1);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.B1);
        board.setPiece(ChessPiece.BLACK_ROOK, ChessSquare.H2);
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.A3, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void BlackDoesNotHangKing() throws Exception
    {
        Position.setInitialPosition(board);
        board.playMove(new ChessMove(ChessSquare.E2, ChessSquare.E4));
        board.playMove(new ChessMove(ChessSquare.A7, ChessSquare.A6));
        board.playMove(new ChessMove(ChessSquare.D1, ChessSquare.H5));
        board.playMove(new ChessMove(ChessSquare.B7, ChessSquare.B6));
        board.playMove(new ChessMove(ChessSquare.F1, ChessSquare.C4));
        Move move = negamax.findBestMove(board);
        assertEquals(ChessSquare.F6, ((ChessMove) move).getEndSquare());
    }
}