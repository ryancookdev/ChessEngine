package software.ryancook.chess.engine;

import org.junit.*;
import software.ryancook.gameengine.Move;
import software.ryancook.chess.util.*;
import java.util.List;
import static org.junit.Assert.*;

public class RuleBookTest
{
    ChessGameState gameState;
    RuleBook ruleBook;

    @Before
    public void setUp() throws Exception
    {
        gameState = new ChessGameState.Builder(Color.WHITE).build();
        ruleBook = new RuleBook();
    }

    @Test
    public void kingMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(8, moves.size());
    }

    @Test
    public void kingMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KING, Square.E4)
            .setPiece(Piece.WHITE_PAWN, Square.D5)
            .setPiece(Piece.WHITE_PAWN, Square.E5)
            .setPiece(Piece.WHITE_PAWN, Square.F5)
            .setPiece(Piece.WHITE_PAWN, Square.D4)
            .setPiece(Piece.WHITE_PAWN, Square.F4)
            .setPiece(Piece.WHITE_PAWN, Square.D3)
            .setPiece(Piece.WHITE_PAWN, Square.E3)
            .setPiece(Piece.WHITE_PAWN, Square.F3)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(3, moves.size());
    }

    @Test
    public void bishopMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_BISHOP, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(13, moves.size());
    }

    @Test
    public void bishopMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_BISHOP, Square.E4)
            .setPiece(Piece.WHITE_PAWN, Square.G2)
            .setPiece(Piece.WHITE_PAWN, Square.D3)
            .setPiece(Piece.WHITE_PAWN, Square.B7)
            .setPiece(Piece.WHITE_PAWN, Square.H7)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(10, moves.size());
    }

    @Test
    public void bishopMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_BISHOP, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.G2)
            .setPiece(Piece.BLACK_PAWN, Square.D3)
            .setPiece(Piece.BLACK_PAWN, Square.B7)
            .setPiece(Piece.BLACK_PAWN, Square.H7)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(9, moves.size());
    }

    @Test
    public void rookMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_ROOK, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(14, moves.size());
    }

    @Test
    public void rookMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_ROOK, Square.E4)
            .setPiece(Piece.WHITE_PAWN, Square.E2)
            .setPiece(Piece.WHITE_PAWN, Square.D4)
            .setPiece(Piece.WHITE_PAWN, Square.E7)
            .setPiece(Piece.WHITE_PAWN, Square.H4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(9, moves.size());
    }

    @Test
    public void rookMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_ROOK, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.E2)
            .setPiece(Piece.BLACK_PAWN, Square.D4)
            .setPiece(Piece.BLACK_PAWN, Square.E7)
            .setPiece(Piece.BLACK_PAWN, Square.H4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(9, moves.size());
    }

    @Test
    public void queenMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_QUEEN, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(27, moves.size());
    }

    @Test
    public void queenMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_QUEEN, Square.E4)
            .setPiece(Piece.WHITE_PAWN, Square.E2)
            .setPiece(Piece.WHITE_PAWN, Square.D4)
            .setPiece(Piece.WHITE_PAWN, Square.E7)
            .setPiece(Piece.WHITE_PAWN, Square.H4)
            .setPiece(Piece.WHITE_PAWN, Square.G2)
            .setPiece(Piece.WHITE_PAWN, Square.D3)
            .setPiece(Piece.WHITE_PAWN, Square.B7)
            .setPiece(Piece.WHITE_PAWN, Square.H7)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(18, moves.size());
    }

    @Test
    public void queenMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_QUEEN, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.E2)
            .setPiece(Piece.BLACK_PAWN, Square.D4)
            .setPiece(Piece.BLACK_PAWN, Square.E7)
            .setPiece(Piece.BLACK_PAWN, Square.H4)
            .setPiece(Piece.BLACK_PAWN, Square.G2)
            .setPiece(Piece.BLACK_PAWN, Square.D3)
            .setPiece(Piece.BLACK_PAWN, Square.B7)
            .setPiece(Piece.BLACK_PAWN, Square.H7)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(18, moves.size());
    }

    @Test
    public void knightMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KNIGHT, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(8, moves.size());
    }

    @Test
    public void knightMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KNIGHT, Square.E4)
            .setPiece(Piece.WHITE_PAWN, Square.D6)
            .setPiece(Piece.WHITE_PAWN, Square.F6)
            .setPiece(Piece.WHITE_PAWN, Square.C5)
            .setPiece(Piece.WHITE_PAWN, Square.G5)
            .setPiece(Piece.WHITE_PAWN, Square.C3)
            .setPiece(Piece.WHITE_PAWN, Square.G3)
            .setPiece(Piece.WHITE_PAWN, Square.D2)
            .setPiece(Piece.WHITE_PAWN, Square.F2)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(10, moves.size());
    }

    @Test
    public void knightMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_KNIGHT, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.D6)
            .setPiece(Piece.BLACK_PAWN, Square.F6)
            .setPiece(Piece.BLACK_PAWN, Square.C5)
            .setPiece(Piece.BLACK_PAWN, Square.G5)
            .setPiece(Piece.BLACK_PAWN, Square.C3)
            .setPiece(Piece.BLACK_PAWN, Square.G3)
            .setPiece(Piece.BLACK_PAWN, Square.D2)
            .setPiece(Piece.BLACK_PAWN, Square.F2)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(8, moves.size());
    }

    @Test
    public void whitePawnSingleMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E4)
            .setPiece(Piece.WHITE_PAWN, Square.E5)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.E5)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }

    @Test
    public void blackPawnSingleMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.E3)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E4)
            .setPiece(Piece.WHITE_PAWN, Square.E3)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E2)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E2)
            .setPiece(Piece.WHITE_PAWN, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E2)
            .setPiece(Piece.BLACK_PAWN, Square.E4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnCapturesBlackPawn() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E4)
            .setPiece(Piece.BLACK_PAWN, Square.D5)
            .setPiece(Piece.BLACK_PAWN, Square.F5)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(3, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesOnEmptyBoard() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E7)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E7)
            .setPiece(Piece.BLACK_PAWN, Square.E5)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E7)
            .setPiece(Piece.WHITE_PAWN, Square.E5)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnCapturesWhitePawn() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E5)
            .setPiece(Piece.WHITE_PAWN, Square.D4)
            .setPiece(Piece.WHITE_PAWN, Square.F4)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(3, moves.size());
    }

    @Test
    public void whitePawnDoubleMoveCannotJumpPiece() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.WHITE)
            .setPiece(Piece.WHITE_PAWN, Square.E2)
            .setPiece(Piece.BLACK_PAWN, Square.E3)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }

    @Test
    public void blackPawnDoubleMoveCannotJumpPiece() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_PAWN, Square.E7)
            .setPiece(Piece.WHITE_PAWN, Square.E6)
            .build();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }

    @Test
    public void notMateOrStalemate() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_KING, Square.A8)
            .setPiece(Piece.WHITE_KING, Square.A6)
            .setPiece(Piece.WHITE_ROOK, Square.A1)
            .build();
        assertFalse(ruleBook.isCheck(gameState));
        assertFalse(ruleBook.isMate(gameState));
        assertFalse(ruleBook.isStalemate(gameState));
    }

    @Test
    public void detectStalemate() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_KING, Square.A8)
            .setPiece(Piece.WHITE_KING, Square.A6)
            .setPiece(Piece.WHITE_ROOK, Square.B1)
            .build();
        assertFalse(ruleBook.isCheck(gameState));
        assertFalse(ruleBook.isMate(gameState));
        assertTrue(ruleBook.isStalemate(gameState));
    }

    @Test
    public void detectMate() throws Exception
    {
        gameState = gameState.getBuilder()
            .setActivePieces(Color.BLACK)
            .setPiece(Piece.BLACK_KING, Square.A8)
            .setPiece(Piece.WHITE_KING, Square.A6)
            .setPiece(Piece.WHITE_ROOK, Square.H8)
            .build();
        assertTrue(ruleBook.isCheck(gameState));
        assertTrue(ruleBook.isMate(gameState));
        assertFalse(ruleBook.isStalemate(gameState));
    }

}