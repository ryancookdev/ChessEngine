package software.ryancook.chess.engine;

import org.junit.*;
import software.ryancook.chess.util.*;
import software.ryancook.gameengine.*;
import static org.junit.Assert.*;

public class EngineMateTest
{
    ChessGameState gameState;
    ChessEvaluator evaluator;
    Negamax negamax;

    @Before
    public void setUp() throws Exception
    {
        gameState = new ChessGameState.Builder(Color.WHITE).build();
        evaluator = new ChessEvaluator();
        negamax = new Negamax(evaluator);
    }

    @Test
    public void whiteFindsBackRankMate() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.BLACK_KING, Square.B8)
            .setPiece(Piece.WHITE_KING, Square.B6)
            .setPiece(Piece.WHITE_ROOK, Square.H1)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.H8, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void blackFindsBackRankMate() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.WHITE_KING, Square.B8)
            .setPiece(Piece.BLACK_KING, Square.B6)
            .setPiece(Piece.BLACK_ROOK, Square.H1)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.H8, ((ChessMove) move).getEndSquare());
    }

    @Test
    public void whiteAvoidsStalemate() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.BLACK_KING, Square.A8)
            .setPiece(Piece.WHITE_KING, Square.A6)
            .setPiece(Piece.WHITE_ROOK, Square.A1)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertNotEquals(Square.B1, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void mustBlockEndgameMate() throws Exception
    {
        ChessGameState gameState = new ChessGameState.Builder(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.A8)
            .setPiece(Piece.WHITE_ROOK, Square.F3)
            .setPiece(Piece.BLACK_PAWN, Square.F4)
            .setPiece(Piece.BLACK_KING, Square.H1)
            .setPiece(Piece.BLACK_ROOK, Square.B1)
            .setPiece(Piece.BLACK_ROOK, Square.H2)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.A3, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void myTest1() throws Exception
    {
        negamax.setMaxTime(1000000);
        ChessGameState gameState = new ChessGameState.Builder(Color.BLACK)
            .setPiece(Piece.WHITE_KING, Square.A8)
            .setPiece(Piece.WHITE_ROOK, Square.A3)
            .setPiece(Piece.BLACK_PAWN, Square.F4)
            .setPiece(Piece.BLACK_KING, Square.H1)
            .setPiece(Piece.BLACK_ROOK, Square.B1)
            .setPiece(Piece.BLACK_ROOK, Square.H2)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.A3, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void myTest2() throws Exception
    {
        negamax.setMaxTime(1000000);
        ChessGameState gameState = new ChessGameState.Builder(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.A8)
            .setPiece(Piece.WHITE_ROOK, Square.A3)
            .setPiece(Piece.BLACK_PAWN, Square.F4)
            .setPiece(Piece.BLACK_KING, Square.H1)
            .setPiece(Piece.BLACK_ROOK, Square.B1)
            .setPiece(Piece.BLACK_ROOK, Square.A2)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.A3, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void blackDoesNotHangKing() throws Exception
    {
        negamax.setMaxTime(10000);
        // Not a good test. There are multiple ways to block mate.
        // Currently d7-d5 is played, which blocks mate but loses a pawn.
        // Given 10 seconds, it plays the illegal f7-f6.
        GameState gameState = Position.getInitialPosition();
        gameState = gameState.playMove(new ChessMove(Square.E2, Square.E4));
        gameState = gameState.playMove(new ChessMove(Square.A7, Square.A6));
        gameState = gameState.playMove(new ChessMove(Square.D1, Square.H5));
        gameState = gameState.playMove(new ChessMove(Square.B7, Square.B6));
        gameState = gameState.playMove(new ChessMove(Square.F1, Square.C4));
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.E6, ((ChessMove) move).getEndSquare());
    }

    @Ignore
    public void rookMateCombination1() throws Exception
    {
        ChessGameState gameState = new ChessGameState.Builder(Color.BLACK)
            .setPiece(Piece.WHITE_KING, Square.H1)
            .setPiece(Piece.WHITE_ROOK, Square.D1)
            .setPiece(Piece.WHITE_PAWN, Square.H2)
            .setPiece(Piece.WHITE_PAWN, Square.G2)
            .setPiece(Piece.WHITE_PAWN, Square.C4)
            .setPiece(Piece.BLACK_KING, Square.G8)
            .setPiece(Piece.BLACK_PAWN, Square.H7)
            .setPiece(Piece.BLACK_PAWN, Square.D6)
            .setPiece(Piece.BLACK_ROOK, Square.F7)
            .setPiece(Piece.BLACK_ROOK, Square.F4)
            .setPiece(Piece.BLACK_QUEEN, Square.C6)
            .build();
        final Move move = negamax.findBestMove(gameState);
        assertEquals(Square.E4, ((ChessMove) move).getEndSquare());
    }
}