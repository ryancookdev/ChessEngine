package software.ryancook.piece;

import org.junit.*;
import software.ryancook.Board;
import software.ryancook.Move;
import software.ryancook.Square;
import java.util.List;
import static org.junit.Assert.*;

public class PawnTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
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

    @Test
    public void whitePawnDoubleMovesOnEmptyBoard() throws Exception
    {
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E2);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(2, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E4);
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E2);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void whitePawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E4);
        piece = new Pawn(Color.WHITE);
        board.setPiece(piece, Square.E2);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesOnEmptyBoard() throws Exception
    {
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E7);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(2, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithFriendsOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.BLACK), Square.E5);
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E7);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }

    @Test
    public void blackPawnDoubleMovesWithEnemiesOnPath() throws Exception
    {
        board.setPiece(new Pawn(Color.WHITE), Square.E5);
        piece = new Pawn(Color.BLACK);
        board.setPiece(piece, Square.E7);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(1, moves.size());
    }
}