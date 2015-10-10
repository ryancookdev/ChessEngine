package software.ryancook.util;

import org.junit.Test;
import software.ryancook.generics.MultiLevelQueue;
import static org.junit.Assert.*;

public class SquareTest
{
    @Test
    public void getRank() throws Exception
    {
        assertEquals(1, Square.A1.getRank());
        assertEquals(1, Square.H1.getRank());
        assertEquals(2, Square.A2.getRank());
        assertEquals(2, Square.H2.getRank());
        assertEquals(3, Square.A3.getRank());
        assertEquals(3, Square.H3.getRank());
        assertEquals(4, Square.A4.getRank());
        assertEquals(4, Square.H4.getRank());
        assertEquals(5, Square.A5.getRank());
        assertEquals(5, Square.H5.getRank());
        assertEquals(6, Square.A6.getRank());
        assertEquals(6, Square.H6.getRank());
        assertEquals(7, Square.A7.getRank());
        assertEquals(7, Square.H7.getRank());
        assertEquals(8, Square.A8.getRank());
        assertEquals(8, Square.H8.getRank());
    }

    @Test
    public void stringToPiece() throws Exception
    {
        assertEquals(Square.A1, Square.getSquare("a1"));
        assertEquals(Square.B2, Square.getSquare("b2"));
        assertEquals(Square.C3, Square.getSquare("c3"));
        assertEquals(Square.D4, Square.getSquare("d4"));
        assertEquals(Square.E5, Square.getSquare("e5"));
        assertEquals(Square.F6, Square.getSquare("f6"));
        assertEquals(Square.G7, Square.getSquare("g7"));
        assertEquals(Square.H8, Square.getSquare("h8"));
    }

    @Test
    public void pieceToString() throws Exception
    {
        assertEquals("a8", Square.A8.getString());
        assertEquals("b7", Square.B7.getString());
        assertEquals("c6", Square.C6.getString());
        assertEquals("d5", Square.D5.getString());
        assertEquals("e4", Square.E4.getString());
        assertEquals("f3", Square.F3.getString());
        assertEquals("g2", Square.G2.getString());
        assertEquals("h1", Square.H1.getString());
    }

    @Test
    public void bishopMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E4.getBishopMoves();
        assertEquals(13, squares.size());

        squares = Square.A1.getBishopMoves();
        assertEquals(7, squares.size());
    }

    @Test
    public void blackPawnMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E5.getBlackPawnMoves();
        assertEquals(3, squares.size());

        squares = Square.A5.getBlackPawnMoves();
        assertEquals(2, squares.size());
    }

    @Test
    public void blackPawnSecondRankMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E7.getBlackPawnMoves();
        assertEquals(4, squares.size());

        squares = Square.A7.getBlackPawnMoves();
        assertEquals(3, squares.size());
    }

    @Test
    public void kingMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E4.getKingMoves();
        assertEquals(8, squares.size());

        squares = Square.A1.getKingMoves();
        assertEquals(3, squares.size());
    }

    @Test
    public void knightMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E4.getKnightMoves();
        assertEquals(8, squares.size());

        squares = Square.A1.getKnightMoves();
        assertEquals(2, squares.size());
    }

    @Test
    public void queenMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E4.getQueenMoves();
        assertEquals(27, squares.size());

        squares = Square.A1.getQueenMoves();
        assertEquals(21, squares.size());
    }

    @Test
    public void rookMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E4.getRookMoves();
        assertEquals(14, squares.size());

        squares = Square.E4.getRookMoves();
        assertEquals(14, squares.size());
    }

    @Test
    public void whitePawnMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E4.getWhitePawnMoves();
        assertEquals(3, squares.size());

        squares = Square.A4.getWhitePawnMoves();
        assertEquals(2, squares.size());
    }

    @Test
    public void whitePawnSecondRankMoves() throws Exception
    {
        MultiLevelQueue<Square> squares = Square.E2.getWhitePawnMoves();
        assertEquals(4, squares.size());

        squares = Square.A2.getWhitePawnMoves();
        assertEquals(3, squares.size());
    }

    @Test
    public void sameDiagonal() throws Exception
    {
        assertFalse(Square.E4.sameDiagonal(Square.E6));
        assertTrue(Square.E4.sameDiagonal(Square.C2));
        assertTrue(Square.E4.sameDiagonal(Square.C6));
        assertTrue(Square.E4.sameDiagonal(Square.G2));
        assertTrue(Square.E4.sameDiagonal(Square.G6));
    }
}