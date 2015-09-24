package software.ryancook.piece;

import org.junit.*;
import software.ryancook.Board;
import software.ryancook.Move;
import software.ryancook.Square;
import java.util.List;
import static org.junit.Assert.*;

public class RookTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
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
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.E4);
        board.setPiece(new Pawn(Color.WHITE), Square.E2);
        board.setPiece(new Pawn(Color.WHITE), Square.D4);
        board.setPiece(new Pawn(Color.WHITE), Square.E7);
        board.setPiece(new Pawn(Color.WHITE), Square.H4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(5, moves.size());
    }

    @Test
    public void rookMovesWithEnemiesOnPath() throws Exception
    {
        piece = new Rook(Color.WHITE);
        board.setPiece(piece, Square.E4);
        board.setPiece(new Pawn(Color.BLACK), Square.E2);
        board.setPiece(new Pawn(Color.BLACK), Square.D4);
        board.setPiece(new Pawn(Color.BLACK), Square.E7);
        board.setPiece(new Pawn(Color.BLACK), Square.H4);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(9, moves.size());
    }
}