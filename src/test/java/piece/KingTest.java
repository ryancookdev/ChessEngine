package software.ryancook.piece;

import org.junit.*;
import software.ryancook.Board;
import software.ryancook.Move;
import software.ryancook.Square;
import java.util.List;
import static org.junit.Assert.*;

public class KingTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
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
}