package software.ryancook.chess.engine;

import org.junit.*;
import software.ryancook.chess.util.*;
import software.ryancook.gameengine.*;
import java.util.List;
import static org.junit.Assert.*;

public class ChessGameStateTest
{
    private ChessGameState gameState;

    @Test
    public void compareDifferentPosition() throws Exception
    {
        GameState kingPawn = Position.getInitialPosition();
        kingPawn = kingPawn.playMove(new ChessMove(Square.E2, Square.E4));

        GameState queenPawn = Position.getInitialPosition();
        queenPawn = queenPawn.playMove(new ChessMove(Square.D2, Square.D4));

        assertNotEquals(kingPawn, queenPawn);
        assertNotEquals(kingPawn.hashCode(), queenPawn.hashCode());
    }

    @Test
    public void compareDifferentPly() throws Exception
    {
        GameState kingPawn = Position.getInitialPosition();
        kingPawn = kingPawn.playMove(new ChessMove(Square.E2, Square.E4));

        GameState slowKingPawn = Position.getInitialPosition();
        slowKingPawn = slowKingPawn.playMove(new ChessMove(Square.B1, Square.C3));
        slowKingPawn = slowKingPawn.playMove(new ChessMove(Square.E2, Square.E4));
        slowKingPawn = slowKingPawn.playMove(new ChessMove(Square.C3, Square.B1));

        assertEquals(kingPawn, slowKingPawn);
        assertEquals(kingPawn.hashCode(), slowKingPawn.hashCode());
    }

    @Test
    public void compareDifferentSideToMove() throws Exception
    {
        final ChessGameState.Builder builder = new ChessGameState.Builder(Color.WHITE);
        builder.setPiece(Piece.BLACK_BISHOP, Square.A1);

        final ChessGameState gameState1 = builder.build();
        final ChessGameState gameState2 = builder.togglePlayerToMove().build();

        assertNotEquals(gameState1, gameState2);
        assertNotEquals(gameState1.hashCode(), gameState2.hashCode());
    }

    @Test
    public void createEmptyBoard() throws Exception
    {
        gameState = new ChessGameState.Builder(Color.WHITE).build();
        Piece piece = gameState.getPiece(Square.A1);
        assertEquals("A1 should not have a piece", Piece.NULL, piece);

        piece = gameState.getPiece(Square.H8);
        assertEquals("H8 should not have a piece", Piece.NULL, piece);
    }

    @Test
    public void initialPosition() throws Exception
    {
        gameState = Position.getInitialPosition();
        assertEquals(Piece.WHITE_ROOK, gameState.getPiece(Square.A1));
        assertEquals(Piece.WHITE_KNIGHT, gameState.getPiece(Square.B1));
        assertEquals(Piece.WHITE_BISHOP, gameState.getPiece(Square.C1));
        assertEquals(Piece.WHITE_QUEEN, gameState.getPiece(Square.D1));
        assertEquals(Piece.WHITE_PAWN, gameState.getPiece(Square.A2));
        assertEquals(Piece.WHITE_PAWN, gameState.getPiece(Square.B2));
        assertEquals(Piece.BLACK_PAWN, gameState.getPiece(Square.G7));
        assertEquals(Piece.BLACK_PAWN, gameState.getPiece(Square.H7));
        assertEquals(Piece.BLACK_KING, gameState.getPiece(Square.E8));
        assertEquals(Piece.BLACK_BISHOP, gameState.getPiece(Square.F8));
        assertEquals(Piece.BLACK_KNIGHT, gameState.getPiece(Square.G8));
        assertEquals(Piece.BLACK_ROOK, gameState.getPiece(Square.H8));
        assertEquals(Piece.NULL, gameState.getPiece(Square.E4));
    }

    @Test
    public void copiedBoardHasNewBoardReference() throws Exception
    {
        gameState = Position.getInitialPosition();
        final ChessGameState newBoard = new ChessGameState(gameState);
        gameState.playMove(new ChessMove(Square.A2, Square.A3));
        assertEquals("There should still be a white pawn on A2", Piece.WHITE_PAWN, newBoard.getPiece(Square.A2));
    }

    @Test
    public void colorToMove() throws Exception
    {
        gameState = Position.getInitialPosition();
        assertEquals(Color.WHITE, gameState.getColorToMove());
    }

    @Test
    public void movePiece() throws Exception
    {
        gameState = Position.getInitialPosition();
        gameState = (ChessGameState) gameState.playMove(new ChessMove(Square.A2, Square.A3));
        assertEquals("A2 should be empty", Piece.NULL, gameState.getPiece(Square.A2));
        assertEquals("There should be a white pawn on A3", Piece.WHITE_PAWN, gameState.getPiece(Square.A3));
    }

    @Test
    public void countPiecesOnBoard() throws Exception
    {
        gameState = Position.getInitialPosition();
        assertEquals("There should be 16 black pieces", 16, gameState.getTotalBlackPieces());
        assertEquals("There should be 16 white pieces", 16, gameState.getTotalWhitePieces());
    }

    @Test
    public void legalMovesForInitialPosition() throws Exception
    {
        gameState = Position.getInitialPosition();
        final RuleBook ruleBook = new RuleBook();
        final List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(20, moves.size());
    }
}