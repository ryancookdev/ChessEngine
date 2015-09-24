package software.ryancook.piece;

import org.junit.*;
import software.ryancook.Board;
import software.ryancook.Move;
import software.ryancook.Square;
import java.util.List;
import static org.junit.Assert.*;

public class QueenTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
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
        piece = new Queen(Color.WHITE);
        board.setPiece(piece, Square.E4);
        board.setPiece(new Pawn(Color.WHITE), Square.E2);
        board.setPiece(new Pawn(Color.WHITE), Square.D4);
        board.setPiece(new Pawn(Color.WHITE), Square.E7);
        board.setPiece(new Pawn(Color.WHITE), Square.H4);
        board.setPiece(new Pawn(Color.WHITE), Square.G2);
        board.setPiece(new Pawn(Color.WHITE), Square.D3);
        board.setPiece(new Pawn(Color.WHITE), Square.B7);
        board.setPiece(new Pawn(Color.WHITE), Square.H7);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(10, moves.size());
    }

    @Test
    public void queenMovesWithEnemiesOnPath() throws Exception
    {
        piece = new Queen(Color.WHITE);
        board.setPiece(piece, Square.E4);
        board.setPiece(new Pawn(Color.BLACK), Square.E2);
        board.setPiece(new Pawn(Color.BLACK), Square.D4);
        board.setPiece(new Pawn(Color.BLACK), Square.E7);
        board.setPiece(new Pawn(Color.BLACK), Square.H4);
        board.setPiece(new Pawn(Color.BLACK), Square.G2);
        board.setPiece(new Pawn(Color.BLACK), Square.D3);
        board.setPiece(new Pawn(Color.BLACK), Square.B7);
        board.setPiece(new Pawn(Color.BLACK), Square.H7);
        List<Move> moves = piece.getLegalMoves();
        assertEquals(18, moves.size());
    }
}