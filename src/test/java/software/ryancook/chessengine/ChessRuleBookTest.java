package software.ryancook.chessengine;

import org.junit.*;
import software.ryancook.chessengine.game.*;
import software.ryancook.gameengine.Move;
import java.util.List;
import static org.junit.Assert.*;

public class ChessRuleBookTest
{
    ChessGameState gameState;
    ChessRuleBook ruleBook;

    @Before
    public void setUp() throws Exception
    {
        gameState = new ChessGameState();
        ruleBook = new ChessRuleBook();
    }

    @Test
    public void kingMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_KING, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(8, moves.size());
    }

    @Test
    public void kingMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_KING, ChessSquare.E4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D5);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E5);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F5);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D3);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E3);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F3);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(3, moves.size());
    }

    @Test
    public void kingMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_KING, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D5);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E5);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F5);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D3);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E3);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F3);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(8, moves.size());
    }

    @Test
    public void bishopMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(13, moves.size());
    }

    @Test
    public void bishopMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.E4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.G2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D3);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.B7);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.H7);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(10, moves.size());
    }

    @Test
    public void bishopMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_BISHOP, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.G2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D3);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.B7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.H7);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(9, moves.size());
    }

    @Test
    public void rookMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(14, moves.size());
    }

    @Test
    public void rookMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.E4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.H4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(9, moves.size());
    }

    @Test
    public void rookMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_ROOK, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.H4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(9, moves.size());
    }

    @Test
    public void queenMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_QUEEN, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(27, moves.size());
    }

    @Test
    public void queenMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_QUEEN, ChessSquare.E4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.H4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.G2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D3);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.B7);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.H7);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(18, moves.size());
    }

    @Test
    public void queenMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_QUEEN, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.H4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.G2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D3);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.B7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.H7);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(18, moves.size());
    }

    @Test
    public void knightMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(8, moves.size());
    }

    @Test
    public void knightMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.E4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D6);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F6);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.C5);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.G5);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.C3);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.G3);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F2);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(10, moves.size());
    }

    @Test
    public void knightMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_KNIGHT, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D6);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F6);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.C5);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.G5);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.C3);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.G3);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F2);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(8, moves.size());
    }

    @Test
    public void whitePawnSingleMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E5);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E5);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }

    @Test
    public void blackPawnSingleMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E3);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E3);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E2);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnCapturesBlackPawn() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E4);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.D5);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.F5);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(3, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesOnEmptyBoard() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E7);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E5);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E5);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnCapturesWhitePawn() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E5);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.D4);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.F4);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(3, moves.size());
    }

    @Test
    public void whitePawnDoubleMoveCannotJumpPiece() throws Exception
    {
        gameState.setActivePieces(Color.WHITE);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E2);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E3);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }

    @Test
    public void blackPawnDoubleMoveCannotJumpPiece() throws Exception
    {
        gameState.setActivePieces(Color.BLACK);
        gameState.setPiece(ChessPiece.BLACK_PAWN, ChessSquare.E7);
        gameState.setPiece(ChessPiece.WHITE_PAWN, ChessSquare.E6);
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(0, moves.size());
    }
}