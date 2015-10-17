package software.ryancook.chessengine;

import org.junit.*;
import software.ryancook.chessengine.game.*;
import software.ryancook.gameengine.Move;
import java.util.List;
import static org.junit.Assert.*;

public class ChessGameStateTest
{
    private ChessGameState gameState;

    @Before
    public void setUp() throws Exception
    {
        gameState = new ChessGameState();
    }

    @Test
    public void compareBoardsWithSamePlyAndDifferentPositions() throws Exception
    {
        ChessGameState kingPawn = new ChessGameState();
        Position.setInitialPosition(kingPawn);
        kingPawn.playMove(new ChessMove(ChessSquare.E2, ChessSquare.E4));

        ChessGameState queenPawn = new ChessGameState();
        Position.setInitialPosition(queenPawn);
        queenPawn.playMove(new ChessMove(ChessSquare.D2, ChessSquare.D4));

        assertNotEquals(kingPawn, queenPawn);
        assertNotEquals(kingPawn.hashCode(), queenPawn.hashCode());
    }

    @Test
    public void compareBoardsWithDifferentPlyAndSamePosition() throws Exception
    {
        ChessGameState kingPawn = new ChessGameState();
        Position.setInitialPosition(kingPawn);
        kingPawn.playMove(new ChessMove(ChessSquare.E2, ChessSquare.E4));

        ChessGameState slowKingPawn = new ChessGameState();
        Position.setInitialPosition(slowKingPawn);
        slowKingPawn.playMove(new ChessMove(ChessSquare.B1, ChessSquare.C3));
        slowKingPawn.playMove(new ChessMove(ChessSquare.E2, ChessSquare.E4));
        slowKingPawn.playMove(new ChessMove(ChessSquare.C3, ChessSquare.B1));

        assertEquals(kingPawn, slowKingPawn);
        assertEquals(kingPawn.hashCode(), slowKingPawn.hashCode());
    }

    @Test
    public void createEmptyBoard() throws Exception
    {
        ChessPiece piece = gameState.getPiece(ChessSquare.A1);
        assertEquals("A1 should not have a piece", ChessPiece.NULL, piece);

        piece = gameState.getPiece(ChessSquare.H8);
        assertEquals("H8 should not have a piece", ChessPiece.NULL, piece);
    }

    @Test
    public void initialPosition() throws Exception
    {
        Position.setInitialPosition(gameState);
        assertEquals(ChessPiece.WHITE_ROOK, gameState.getPiece(ChessSquare.A1));
        assertEquals(ChessPiece.WHITE_KNIGHT, gameState.getPiece(ChessSquare.B1));
        assertEquals(ChessPiece.WHITE_BISHOP, gameState.getPiece(ChessSquare.C1));
        assertEquals(ChessPiece.WHITE_QUEEN, gameState.getPiece(ChessSquare.D1));
        assertEquals(ChessPiece.WHITE_PAWN, gameState.getPiece(ChessSquare.A2));
        assertEquals(ChessPiece.WHITE_PAWN, gameState.getPiece(ChessSquare.B2));
        assertEquals(ChessPiece.BLACK_PAWN, gameState.getPiece(ChessSquare.G7));
        assertEquals(ChessPiece.BLACK_PAWN, gameState.getPiece(ChessSquare.H7));
        assertEquals(ChessPiece.BLACK_KING, gameState.getPiece(ChessSquare.E8));
        assertEquals(ChessPiece.BLACK_BISHOP, gameState.getPiece(ChessSquare.F8));
        assertEquals(ChessPiece.BLACK_KNIGHT, gameState.getPiece(ChessSquare.G8));
        assertEquals(ChessPiece.BLACK_ROOK, gameState.getPiece(ChessSquare.H8));
        assertEquals(ChessPiece.NULL, gameState.getPiece(ChessSquare.E4));
    }

    @Test
    public void copiedBoardHasNewBoardReference() throws Exception
    {
        Position.setInitialPosition(gameState);
        ChessGameState newBoard = new ChessGameState(gameState);
        gameState.playMove(new ChessMove(ChessSquare.A2, ChessSquare.A3));
        assertEquals("There should still be a white pawn on A2", ChessPiece.WHITE_PAWN, newBoard.getPiece(ChessSquare.A2));
    }

    @Test
    public void copiedBoardHasNewPieceListReference() throws Exception
    {
        Position.setInitialPosition(gameState);
        ChessGameState newBoard = new ChessGameState(gameState);
        gameState.setPiece(ChessPiece.NULL, ChessSquare.A2);
        assertEquals(16, newBoard.getTotalWhitePieces());
        assertEquals(15, gameState.getTotalWhitePieces());
    }

    @Test
    public void colorToMove() throws Exception
    {
        Position.setInitialPosition(gameState);
        assertEquals(Color.WHITE, gameState.getColorToMove());
    }

    @Test
    public void movePiece() throws Exception
    {
        Position.setInitialPosition(gameState);
        gameState.playMove(new ChessMove(ChessSquare.A2, ChessSquare.A3));
        assertEquals("A2 should be empty", ChessPiece.NULL, gameState.getPiece(ChessSquare.A2));
        assertEquals("There should be a white pawn on A3", ChessPiece.WHITE_PAWN, gameState.getPiece(ChessSquare.A3));
    }

    @Test
    public void countPiecesOnBoard() throws Exception
    {
        Position.setInitialPosition(gameState);
        assertEquals("There should be 16 black pieces", 16, gameState.getTotalBlackPieces());
        assertEquals("There should be 16 white pieces", 16, gameState.getTotalWhitePieces());
    }

    @Test
    public void legalMovesForInitialPosition() throws Exception
    {
        Position.setInitialPosition(gameState);
        ChessRuleBook ruleBook = new ChessRuleBook();
        List<Move> moves = ruleBook.getMoves(gameState);
        assertEquals(20, moves.size());
    }
}