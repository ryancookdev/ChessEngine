package software.ryancook.piece;

import org.junit.*;
import software.ryancook.Board;
import software.ryancook.Move;
import software.ryancook.Square;
import java.util.List;
import static org.junit.Assert.*;

public class BishopTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
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
}