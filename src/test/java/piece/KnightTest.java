package software.ryancook.piece;

import org.junit.*;
import software.ryancook.Board;
import software.ryancook.Move;
import software.ryancook.Square;
import java.util.List;
import static org.junit.Assert.*;

public class KnightTest
{
    Board board;
    Piece piece;

    @Before
    public void setUp() throws Exception
    {
        board = new Board();
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
}