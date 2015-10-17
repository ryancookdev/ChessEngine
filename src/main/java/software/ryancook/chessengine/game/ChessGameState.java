package software.ryancook.chessengine.game;

import software.ryancook.gameengine.*;
import java.util.*;

public class ChessGameState implements GameState
{
    private HashMap<ChessSquare, ChessPiece> blackPieces;
    private HashMap<ChessSquare, ChessPiece> whitePieces;
    private HashMap<ChessSquare, ChessPiece> activePieces;
    private int ply;

    public ChessGameState()
    {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();
        activePieces = null;
        ply = 0;
    }

    public ChessGameState(ChessGameState oldBoard)
    {
        this();

        copyPieceList(oldBoard.whitePieces);
        copyPieceList(oldBoard.blackPieces);

        if (oldBoard.getColorToMove() == Color.WHITE) {
            activePieces = whitePieces;
        } else {
            activePieces = blackPieces;
        }
        ply = oldBoard.getPly();
    }

    private void copyPieceList(HashMap<ChessSquare, ChessPiece> oldPieceList)
    {
        Set<ChessSquare> pieces = oldPieceList.keySet();
        for (ChessSquare square: pieces) {
            ChessPiece piece = oldPieceList.get(square);
            setPiece(piece, square);
        }
    }

    public Color getColorToMove()
    {
        return (activePieces == whitePieces ? Color.WHITE : Color.BLACK);
    }

    public void toggleColorToMove()
    {
        Color newColor = (getColorToMove() == Color.BLACK ? Color.WHITE : Color.BLACK);
        setActivePieces(newColor);
    }

    @Override
    public int getPly()
    {
        return ply;
    }

    @Override
    public boolean isFirstPlayerToMove()
    {
        return getColorToMove() == Color.WHITE;
    }

    @Override
    public List<Move> getMoves()
    {
        ChessRuleBook ruleBook = new ChessRuleBook();
        List<Move> moves = ruleBook.getMoves(this);
        return moves;
    }

    @Override
    public Move getNullMove()
    {
        return new ChessMove();
    }

    @Override
    public GameState playMove(Move m)
    {
        ChessMove move = (ChessMove) m;
        ChessGameState gameState = new ChessGameState(this);
        ChessPiece piece = gameState.getPiece(move.getStartSquare());
        if (piece == ChessPiece.NULL) {
            throw new PieceNotFoundException();
        }
        gameState.setPiece(piece, move.getEndSquare());
        gameState.removeFromPieceList(move.getStartSquare());
        gameState.togglePlayerToMove();
        gameState.setPly(gameState.getPly() + 1);
        return gameState;
    }

    @Override
    public List<Move> getCriticalMoves()
    {
        return new ArrayList<>();
    }

    public void setPly(int ply)
    {
        this.ply = ply;
    }

    public ChessPiece getPiece(ChessSquare square)
    {
        if (whitePieces.containsKey(square)) {
            return whitePieces.get(square);
        }
        if (blackPieces.containsKey(square)) {
            return blackPieces.get(square);
        }
        return ChessPiece.NULL;
    }

    private void togglePlayerToMove()
    {
        activePieces = (activePieces == whitePieces ? blackPieces : whitePieces);
    }

    public void setPiece(ChessPiece piece, ChessSquare square)
    {
        removeFromPieceList(square);
        addToPieceList(square, piece);
    }

    private void removeFromPieceList(ChessSquare square)
    {
        ChessPiece piece = getPiece(square);
        if (piece.isBlack()) {
            blackPieces.remove(square);
        } else if (piece.isWhite()) {
            whitePieces.remove(square);
        }
    }

    public void addToPieceList(ChessSquare square, ChessPiece piece)
    {
        if (piece.isBlack()) {
            blackPieces.put(square, piece);
        } else if (piece.isWhite()) {
            whitePieces.put(square, piece);
        }
    }

    public HashMap<ChessSquare, ChessPiece> getWhitePieces()
    {
        return whitePieces;
    }

    public HashMap<ChessSquare, ChessPiece> getBlackPieces()
    {
        return blackPieces;
    }

    public HashMap<ChessSquare, ChessPiece> getActivePieces()
    {
        return activePieces;
    }

    public int getTotalWhitePieces()
    {
        int total = 0;
        Set<ChessSquare> pieces = whitePieces.keySet();
        for (ChessSquare square: pieces) {
            if (whitePieces.get(square) != ChessPiece.NULL) {
                total++;
            }
        }
        return total;
    }

    public int getTotalBlackPieces()
    {
        int total = 0;
        Set<ChessSquare> pieces = blackPieces.keySet();
        for (ChessSquare square: pieces) {
            if (blackPieces.get(square) != ChessPiece.NULL) {
                total++;
            }
        }
        return total;
    }

    public void setActivePieces(Color color)
    {
        if (color == Color.WHITE) {
            activePieces = whitePieces;
        } else {
            activePieces = blackPieces;
        }
    }

    @Override
    public String toString()
    {
        ChessPiece[] board = new ChessPiece[128];
        Set<ChessSquare> pieces = blackPieces.keySet();
        for (ChessSquare square: pieces) {
            board[square.getValue()] = blackPieces.get(square);
        }
        pieces = whitePieces.keySet();
        for (ChessSquare square: pieces) {
            board[square.getValue()] = whitePieces.get(square);
        }
        String position = " ";
        for (int i = 0; i < 64; i++) {
            byte square = (byte) ((i + 112) - (24 * (Math.floorDiv(i, 8))));
            ChessPiece piece = board[square];
            position += (piece == null ? ". " : piece.getLetter() + " ");
            if ((i + 1) % 8 == 0) {
                position += "\n ";
            }
        }
        return position;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChessGameState board = (ChessGameState) o;

        if (!blackPieces.equals(board.blackPieces)) {
            return false;
        }
        if (!whitePieces.equals(board.whitePieces)) {
            return false;
        }
        return getColorToMove() == board.getColorToMove();
    }

    @Override
    public int hashCode()
    {
        int result = blackPieces != null ? blackPieces.hashCode() : 0;
        result = 31 * result + (whitePieces != null ? whitePieces.hashCode() : 0);
        result = 31 * result + (activePieces != null ? activePieces.hashCode() : 0);
        return result;
    }

    class PieceNotFoundException extends RuntimeException {}
}
