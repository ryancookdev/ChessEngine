package software.ryancook;

import org.junit.*;
import software.ryancook.piece.*;
import java.util.List;
import static org.junit.Assert.*;

public class PieceTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
    }

    @Test
    public void createPiece() throws Exception
    {
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.A1);
        assertEquals(Movement.WHITE_ROOK, piece.getType());
        assertEquals(Square.A1, piece.getSquare());
    }

    @Test
    public void pieceIsOnTheBoard() throws Exception
    {
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.A1);
        assertEquals(board, piece.getBoard());
    }

    @Test
    public void bishopMovesOnEmptyBoard() throws Exception
    {
        piece = new Bishop(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(13, moves.size());
    }

    @Test
    public void bishopMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.G2);
        board.setPiece(new Pawn(Color.WHITE), Square.D3);
        board.setPiece(new Pawn(Color.WHITE), Square.B7);
        board.setPiece(new Pawn(Color.WHITE), Square.H7);
        piece = new Bishop(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(5, moves.size());
    }

    @Test
    public void bishopMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.G2);
        board.setPiece(new Pawn(Color.BLACK), Square.D3);
        board.setPiece(new Pawn(Color.BLACK), Square.B7);
        board.setPiece(new Pawn(Color.BLACK), Square.H7);
        piece = new Bishop(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(9, moves.size());
    }

    @Test
    public void rookMovesOnEmptyBoard() throws Exception
    {
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(14, moves.size());
    }

    @Test
    public void rookMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E2);
        board.setPiece(new Pawn(Color.WHITE), Square.D4);
        board.setPiece(new Pawn(Color.WHITE), Square.E7);
        board.setPiece(new Pawn(Color.WHITE), Square.H4);
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(5, moves.size());
    }

    @Test
    public void rookMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E2);
        board.setPiece(new Pawn(Color.BLACK), Square.D4);
        board.setPiece(new Pawn(Color.BLACK), Square.E7);
        board.setPiece(new Pawn(Color.BLACK), Square.H4);
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(9, moves.size());
    }

    @Test
    public void queenMovesOnEmptyBoard() throws Exception
    {
        piece = new Queen(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(27, moves.size());
    }

    @Test
    public void queenMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E2);
        board.setPiece(new Pawn(Color.WHITE), Square.D4);
        board.setPiece(new Pawn(Color.WHITE), Square.E7);
        board.setPiece(new Pawn(Color.WHITE), Square.H4);
        board.setPiece(new Pawn(Color.WHITE), Square.G2);
        board.setPiece(new Pawn(Color.WHITE), Square.D3);
        board.setPiece(new Pawn(Color.WHITE), Square.B7);
        board.setPiece(new Pawn(Color.WHITE), Square.H7);
        piece = new Queen(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(10, moves.size());
    }

    @Test
    public void queenMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E2);
        board.setPiece(new Pawn(Color.BLACK), Square.D4);
        board.setPiece(new Pawn(Color.BLACK), Square.E7);
        board.setPiece(new Pawn(Color.BLACK), Square.H4);
        board.setPiece(new Pawn(Color.BLACK), Square.G2);
        board.setPiece(new Pawn(Color.BLACK), Square.D3);
        board.setPiece(new Pawn(Color.BLACK), Square.B7);
        board.setPiece(new Pawn(Color.BLACK), Square.H7);
        piece = new Queen(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(18, moves.size());
    }

    @Test
    public void kingMovesOnEmptyBoard() throws Exception
    {
        piece = new King(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(8, moves.size());
    }

    @Test
    public void kingMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.D5);
        board.setPiece(new Pawn(Color.WHITE), Square.E5);
        board.setPiece(new Pawn(Color.WHITE), Square.F5);
        board.setPiece(new Pawn(Color.WHITE), Square.D4);
        board.setPiece(new Pawn(Color.WHITE), Square.F4);
        board.setPiece(new Pawn(Color.WHITE), Square.D3);
        board.setPiece(new Pawn(Color.WHITE), Square.E3);
        board.setPiece(new Pawn(Color.WHITE), Square.F3);
        piece = new King(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void kingMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.D5);
        board.setPiece(new Pawn(Color.BLACK), Square.E5);
        board.setPiece(new Pawn(Color.BLACK), Square.F5);
        board.setPiece(new Pawn(Color.BLACK), Square.D4);
        board.setPiece(new Pawn(Color.BLACK), Square.F4);
        board.setPiece(new Pawn(Color.BLACK), Square.D3);
        board.setPiece(new Pawn(Color.BLACK), Square.E3);
        board.setPiece(new Pawn(Color.BLACK), Square.F3);
        piece = new King(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(8, moves.size());
    }

    @Test
    public void knightMovesOnEmptyBoard() throws Exception
    {
        piece = new Knight(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(8, moves.size());
    }

    @Test
    public void knightMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.D6);
        board.setPiece(new Pawn(Color.WHITE), Square.F6);
        board.setPiece(new Pawn(Color.WHITE), Square.C5);
        board.setPiece(new Pawn(Color.WHITE), Square.G5);
        board.setPiece(new Pawn(Color.WHITE), Square.C3);
        board.setPiece(new Pawn(Color.WHITE), Square.G3);
        board.setPiece(new Pawn(Color.WHITE), Square.D2);
        board.setPiece(new Pawn(Color.WHITE), Square.F2);
        piece = new Knight(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void knightMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.D6);
        board.setPiece(new Pawn(Color.BLACK), Square.F6);
        board.setPiece(new Pawn(Color.BLACK), Square.C5);
        board.setPiece(new Pawn(Color.BLACK), Square.G5);
        board.setPiece(new Pawn(Color.BLACK), Square.C3);
        board.setPiece(new Pawn(Color.BLACK), Square.G3);
        board.setPiece(new Pawn(Color.BLACK), Square.D2);
        board.setPiece(new Pawn(Color.BLACK), Square.F2);
        piece = new Knight(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(8, moves.size());
    }

    @Test
    public void whitePawnSingleMovesOnEmptyBoard() throws Exception
    {
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E5);
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void whitePawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E5);
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void blackPawnSingleMovesOnEmptyBoard() throws Exception
    {
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E3);
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(0, moves.size());
    }

    @Test
    public void blackPawnSingleMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E3);
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(0, moves.size());
    }
/*
    @Test
    public void whitePawnDoubleMovesOnEmptyBoard() throws Exception
    {
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E5);
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E5);
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesOnEmptyBoard() throws Exception
    {
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E3);
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E3);
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }
*/}
