package software.ryancook;

import software.ryancook.util.*;
import java.util.*;

public class Board
{
    private HashMap<Square, Piece> blackPieces;
    private HashMap<Square, Piece> whitePieces;
    private HashMap<Square, Piece> activePieces;
    private int ply;

    public Board()
    {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();
        activePieces = null;
        ply = 0;
    }

    public Board(Board oldBoard)
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

    private void copyPieceList(HashMap<Square, Piece> oldPieceList)
    {
        Set<Square> pieces = oldPieceList.keySet();
        for (Square square: pieces) {
            Piece piece = oldPieceList.get(square);
            setPiece(piece, square);
        }
    }

    public Color getColorToMove()
    {
        return (activePieces == whitePieces ? Color.WHITE : Color.BLACK);
    }

    public int getPly()
    {
        return ply;
    }

    public void setPly(int ply)
    {
        this.ply = ply;
    }

    public Piece getPiece(Square square)
    {
        if (whitePieces.containsKey(square)) {
            return whitePieces.get(square);
        }
        if (blackPieces.containsKey(square)) {
            return blackPieces.get(square);
        }
        return Piece.NULL;
    }

    public void movePiece(Move move)
    {
        Piece piece = getPiece(move.getStartSquare());
        if (piece == Piece.NULL) {
            throw new PieceNotFoundException();
        }
        setPiece(piece, move.getEndSquare());
        removeFromPieceList(move.getStartSquare());
        togglePlayerToMove();
        ply++;
    }

    private void togglePlayerToMove()
    {
        activePieces = (activePieces == whitePieces ? blackPieces : whitePieces);
    }

    public void setPiece(Piece piece, Square square)
    {
        removeFromPieceList(square);
        addToPieceList(square, piece);
    }

    private void removeFromPieceList(Square square)
    {
        Piece piece = getPiece(square);
        if (piece.isBlack()) {
            blackPieces.remove(square);
        } else if (piece.isWhite()) {
            whitePieces.remove(square);
        }
    }

    public void addToPieceList(Square square, Piece piece)
    {
        if (piece.isBlack()) {
            blackPieces.put(square, piece);
        } else if (piece.isWhite()) {
            whitePieces.put(square, piece);
        }
    }

    public HashMap<Square, Piece> getWhitePieces()
    {
        return whitePieces;
    }

    public HashMap<Square, Piece> getBlackPieces()
    {
        return blackPieces;
    }

    public HashMap<Square, Piece> getActivePieces()
    {
        return activePieces;
    }

    public int getTotalWhitePieces()
    {
        int total = 0;
        Set<Square> pieces = whitePieces.keySet();
        for (Square square: pieces) {
            if (whitePieces.get(square) != Piece.NULL) {
                total++;
            }
        }
        return total;
    }

    public int getTotalBlackPieces()
    {
        int total = 0;
        Set<Square> pieces = blackPieces.keySet();
        for (Square square: pieces) {
            if (blackPieces.get(square) != Piece.NULL) {
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
        Piece[] board = new Piece[128];
        Set<Square> pieces = blackPieces.keySet();
        for (Square square: pieces) {
            board[square.getValue()] = blackPieces.get(square);
        }
        pieces = whitePieces.keySet();
        for (Square square: pieces) {
            board[square.getValue()] = whitePieces.get(square);
        }
        String position = " ";
        for (int i = 0; i < 64; i++) {
            byte square = (byte) ((i + 112) - (24 * (Math.floorDiv(i, 8))));
            Piece piece = board[square];
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

        Board board = (Board) o;

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
