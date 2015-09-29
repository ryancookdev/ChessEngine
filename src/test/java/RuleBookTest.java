package software.ryancook;

import org.junit.*;

import java.util.List;
import static org.junit.Assert.*;

public class RuleBookTest
{
    Board board;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
    }

    @Test
    public void kingMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KING, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(8, moves.size());
    }

    @Test
    public void kingMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KING, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.D5);
        board.setPiece(Piece.WHITE_PAWN, Square.E5);
        board.setPiece(Piece.WHITE_PAWN, Square.F5);
        board.setPiece(Piece.WHITE_PAWN, Square.D4);
        board.setPiece(Piece.WHITE_PAWN, Square.F4);
        board.setPiece(Piece.WHITE_PAWN, Square.D3);
        board.setPiece(Piece.WHITE_PAWN, Square.E3);
        board.setPiece(Piece.WHITE_PAWN, Square.F3);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(3, moves.size());
    }

    @Test
    public void kingMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KING, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.D5);
        board.setPiece(Piece.BLACK_PAWN, Square.E5);
        board.setPiece(Piece.BLACK_PAWN, Square.F5);
        board.setPiece(Piece.BLACK_PAWN, Square.D4);
        board.setPiece(Piece.BLACK_PAWN, Square.F4);
        board.setPiece(Piece.BLACK_PAWN, Square.D3);
        board.setPiece(Piece.BLACK_PAWN, Square.E3);
        board.setPiece(Piece.BLACK_PAWN, Square.F3);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(8, moves.size());
    }

    @Test
    public void bishopMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_BISHOP, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(13, moves.size());
    }

    @Test
    public void bishopMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_BISHOP, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.G2);
        board.setPiece(Piece.WHITE_PAWN, Square.D3);
        board.setPiece(Piece.WHITE_PAWN, Square.B7);
        board.setPiece(Piece.WHITE_PAWN, Square.H7);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(10, moves.size());
    }

    @Test
    public void bishopMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_BISHOP, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.G2);
        board.setPiece(Piece.BLACK_PAWN, Square.D3);
        board.setPiece(Piece.BLACK_PAWN, Square.B7);
        board.setPiece(Piece.BLACK_PAWN, Square.H7);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(9, moves.size());
    }

    @Test
    public void rookMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_ROOK, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(14, moves.size());
    }

    @Test
    public void rookMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_ROOK, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.E2);
        board.setPiece(Piece.WHITE_PAWN, Square.D4);
        board.setPiece(Piece.WHITE_PAWN, Square.E7);
        board.setPiece(Piece.WHITE_PAWN, Square.H4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(9, moves.size());
    }

    @Test
    public void rookMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_ROOK, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.E2);
        board.setPiece(Piece.BLACK_PAWN, Square.D4);
        board.setPiece(Piece.BLACK_PAWN, Square.E7);
        board.setPiece(Piece.BLACK_PAWN, Square.H4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(9, moves.size());
    }

    @Test
    public void queenMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_QUEEN, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(27, moves.size());
    }

    @Test
    public void queenMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_QUEEN, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.E2);
        board.setPiece(Piece.WHITE_PAWN, Square.D4);
        board.setPiece(Piece.WHITE_PAWN, Square.E7);
        board.setPiece(Piece.WHITE_PAWN, Square.H4);
        board.setPiece(Piece.WHITE_PAWN, Square.G2);
        board.setPiece(Piece.WHITE_PAWN, Square.D3);
        board.setPiece(Piece.WHITE_PAWN, Square.B7);
        board.setPiece(Piece.WHITE_PAWN, Square.H7);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(18, moves.size());
    }

    @Test
    public void queenMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_QUEEN, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.E2);
        board.setPiece(Piece.BLACK_PAWN, Square.D4);
        board.setPiece(Piece.BLACK_PAWN, Square.E7);
        board.setPiece(Piece.BLACK_PAWN, Square.H4);
        board.setPiece(Piece.BLACK_PAWN, Square.G2);
        board.setPiece(Piece.BLACK_PAWN, Square.D3);
        board.setPiece(Piece.BLACK_PAWN, Square.B7);
        board.setPiece(Piece.BLACK_PAWN, Square.H7);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(18, moves.size());
    }

    @Test
    public void knightMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(8, moves.size());
    }

    @Test
    public void knightMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.D6);
        board.setPiece(Piece.WHITE_PAWN, Square.F6);
        board.setPiece(Piece.WHITE_PAWN, Square.C5);
        board.setPiece(Piece.WHITE_PAWN, Square.G5);
        board.setPiece(Piece.WHITE_PAWN, Square.C3);
        board.setPiece(Piece.WHITE_PAWN, Square.G3);
        board.setPiece(Piece.WHITE_PAWN, Square.D2);
        board.setPiece(Piece.WHITE_PAWN, Square.F2);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(10, moves.size());
    }

    @Test
    public void knightMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_KNIGHT, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.D6);
        board.setPiece(Piece.BLACK_PAWN, Square.F6);
        board.setPiece(Piece.BLACK_PAWN, Square.C5);
        board.setPiece(Piece.BLACK_PAWN, Square.G5);
        board.setPiece(Piece.BLACK_PAWN, Square.C3);
        board.setPiece(Piece.BLACK_PAWN, Square.G3);
        board.setPiece(Piece.BLACK_PAWN, Square.D2);
        board.setPiece(Piece.BLACK_PAWN, Square.F2);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(8, moves.size());
    }

    @Test
    public void whitePawnSingleMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.E5);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.E5);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(0, moves.size());
    }

    @Test
    public void blackPawnSingleMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_PAWN, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_PAWN, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.E3);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_PAWN, Square.E4);
        board.setPiece(Piece.WHITE_PAWN, Square.E3);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(0, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.E2);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.E2);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.E2);
        board.setPiece(Piece.BLACK_PAWN, Square.E4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnCapturesBlackPawn() throws Exception
    {
        board.setActivePieces(Color.WHITE);
        board.setPiece(Piece.WHITE_PAWN, Square.E4);
        board.setPiece(Piece.BLACK_PAWN, Square.D5);
        board.setPiece(Piece.BLACK_PAWN, Square.F5);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(3, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesOnEmptyBoard() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_PAWN, Square.E7);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_PAWN, Square.E7);
        board.setPiece(Piece.BLACK_PAWN, Square.E5);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_PAWN, Square.E7);
        board.setPiece(Piece.WHITE_PAWN, Square.E5);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnCapturesWhitePawn() throws Exception
    {
        board.setActivePieces(Color.BLACK);
        board.setPiece(Piece.BLACK_PAWN, Square.E5);
        board.setPiece(Piece.WHITE_PAWN, Square.D4);
        board.setPiece(Piece.WHITE_PAWN, Square.F4);
        List<Move> moves = RuleBook.getLegalMoves(board);
        assertEquals(3, moves.size());
    }
}