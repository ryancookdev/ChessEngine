package software.ryancook.chessengine.util;

import org.junit.Test;
import software.ryancook.generics.MultiLevelQueue;
import software.ryancook.chessengine.game.*;
import static org.junit.Assert.*;

public class SquareTest
{
    @Test
    public void getRank() throws Exception
    {
        assertEquals(1, ChessSquare.A1.getRank());
        assertEquals(1, ChessSquare.H1.getRank());
        assertEquals(2, ChessSquare.A2.getRank());
        assertEquals(2, ChessSquare.H2.getRank());
        assertEquals(3, ChessSquare.A3.getRank());
        assertEquals(3, ChessSquare.H3.getRank());
        assertEquals(4, ChessSquare.A4.getRank());
        assertEquals(4, ChessSquare.H4.getRank());
        assertEquals(5, ChessSquare.A5.getRank());
        assertEquals(5, ChessSquare.H5.getRank());
        assertEquals(6, ChessSquare.A6.getRank());
        assertEquals(6, ChessSquare.H6.getRank());
        assertEquals(7, ChessSquare.A7.getRank());
        assertEquals(7, ChessSquare.H7.getRank());
        assertEquals(8, ChessSquare.A8.getRank());
        assertEquals(8, ChessSquare.H8.getRank());
    }

    @Test
    public void stringToPiece() throws Exception
    {
        assertEquals(ChessSquare.A1, ChessSquare.getSquare("a1"));
        assertEquals(ChessSquare.B2, ChessSquare.getSquare("b2"));
        assertEquals(ChessSquare.C3, ChessSquare.getSquare("c3"));
        assertEquals(ChessSquare.D4, ChessSquare.getSquare("d4"));
        assertEquals(ChessSquare.E5, ChessSquare.getSquare("e5"));
        assertEquals(ChessSquare.F6, ChessSquare.getSquare("f6"));
        assertEquals(ChessSquare.G7, ChessSquare.getSquare("g7"));
        assertEquals(ChessSquare.H8, ChessSquare.getSquare("h8"));
    }

    @Test
    public void pieceToString() throws Exception
    {
        assertEquals("a8", ChessSquare.A8.getString());
        assertEquals("b7", ChessSquare.B7.getString());
        assertEquals("c6", ChessSquare.C6.getString());
        assertEquals("d5", ChessSquare.D5.getString());
        assertEquals("e4", ChessSquare.E4.getString());
        assertEquals("f3", ChessSquare.F3.getString());
        assertEquals("g2", ChessSquare.G2.getString());
        assertEquals("h1", ChessSquare.H1.getString());
    }

    @Test
    public void bishopMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E4.getBishopMoves();
        assertEquals(13, squares.size());

        squares = ChessSquare.A1.getBishopMoves();
        assertEquals(7, squares.size());
    }

    @Test
    public void blackPawnMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E5.getBlackPawnMoves();
        assertEquals(3, squares.size());

        squares = ChessSquare.A5.getBlackPawnMoves();
        assertEquals(2, squares.size());
    }

    @Test
    public void blackPawnSecondRankMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E7.getBlackPawnMoves();
        assertEquals(4, squares.size());

        squares = ChessSquare.A7.getBlackPawnMoves();
        assertEquals(3, squares.size());
    }

    @Test
    public void kingMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E4.getKingMoves();
        assertEquals(8, squares.size());

        squares = ChessSquare.A1.getKingMoves();
        assertEquals(3, squares.size());
    }

    @Test
    public void knightMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E4.getKnightMoves();
        assertEquals(8, squares.size());

        squares = ChessSquare.A1.getKnightMoves();
        assertEquals(2, squares.size());
    }

    @Test
    public void queenMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E4.getQueenMoves();
        assertEquals(27, squares.size());

        squares = ChessSquare.A1.getQueenMoves();
        assertEquals(21, squares.size());
    }

    @Test
    public void rookMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E4.getRookMoves();
        assertEquals(14, squares.size());

        squares = ChessSquare.E4.getRookMoves();
        assertEquals(14, squares.size());
    }

    @Test
    public void whitePawnMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E4.getWhitePawnMoves();
        assertEquals(3, squares.size());

        squares = ChessSquare.A4.getWhitePawnMoves();
        assertEquals(2, squares.size());
    }

    @Test
    public void whitePawnSecondRankMoves() throws Exception
    {
        MultiLevelQueue<ChessSquare> squares = ChessSquare.E2.getWhitePawnMoves();
        assertEquals(4, squares.size());

        squares = ChessSquare.A2.getWhitePawnMoves();
        assertEquals(3, squares.size());
    }

    @Test
    public void sameDiagonal() throws Exception
    {
        assertFalse(ChessSquare.E4.sameDiagonal(ChessSquare.E6));
        assertTrue(ChessSquare.E4.sameDiagonal(ChessSquare.C2));
        assertTrue(ChessSquare.E4.sameDiagonal(ChessSquare.C6));
        assertTrue(ChessSquare.E4.sameDiagonal(ChessSquare.G2));
        assertTrue(ChessSquare.E4.sameDiagonal(ChessSquare.G6));
    }
}