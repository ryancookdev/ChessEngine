package software.ryancook;

import software.ryancook.piece.*;
import java.util.*;

public class Board
{
    private Piece[] board;
    private Piece[] blackPieces;
    private Piece[] whitePieces;
    private Piece[] activePieces;

    public Board()
    {
        board = new Piece[128];
        blackPieces = new Piece[16];
        whitePieces = new Piece[16];
        activePieces = null;
    }

    public Piece getPiece(byte square)
    {
        return board[square];
    }

    public void setPiece(Piece piece, byte square)
    {
        piece.setLocation(this, square);
        setPieceOnBoard(square, piece);
        if (piece.getType() < 0) {
            updatePieceRecord(blackPieces, piece);
        } else {
            updatePieceRecord(whitePieces, piece);
        }
    }

    private void updatePieceRecord(Piece[] pieceRecord, Piece piece)
    {
        for (int i = 0; i < 16; i++) {
            if (pieceRecord[i] == null) {
                pieceRecord[i] = piece;
                return;
            }
        }
    }

    private void setPieceOnBoard(byte square, Piece piece)
    {
        board[square] = piece;
    }

    public void movePiece(Move move)
    {
        togglePlayerToMove();
        removeFromActivePieceList(board[move.endSquare]);
        updateMovedPieceAndClearSquare(move);
    }

    private void updateMovedPieceAndClearSquare(Move move)
    {
        Piece piece = board[move.startSquare];
        board[move.endSquare] = piece;
        board[move.startSquare] = null;
        piece.setSquare(move.endSquare);
    }

    private void togglePlayerToMove() {
        activePieces = (activePieces == whitePieces ? blackPieces : whitePieces);
    }

    private void removeFromActivePieceList(Piece capturedPiece)
    {
        for (int i = 0; i < activePieces.length; i++) {
            if (activePieces[i] == capturedPiece) {
                activePieces[i] = null;
            }
        }
    }

    public List<Piece> getWhitePieces()
    {
        List<Piece> pieces = new ArrayList<>(Arrays.asList(whitePieces));
        pieces.removeAll(Collections.singleton(null));
        return pieces;
    }

    public List<Piece> getBlackPieces()
    {
        List<Piece> pieces = new ArrayList<>(Arrays.asList(blackPieces));
        pieces.removeAll(Collections.singleton(null));
        return pieces;
    }

    public int getTotalWhitePieces()
    {
        int total = 0;
        for (int i = 0; i < whitePieces.length; i++) {
            if (whitePieces[i] != null) {
                total++;
            }
        }
        return total;
    }

    public int getTotalBlackPieces()
    {
        int total = 0;
        for (int i = 0; i < blackPieces.length; i++) {
            if (blackPieces[i] != null) {
                total++;
            }
        }
        return total;
    }

    public List<Move> getLegalMoves()
    {
        List<Move> moves = new ArrayList<>();
        for (Piece piece : activePieces) {
            if (piece != null) {
                moves.addAll(piece.getLegalMoves());
            }
        }
        return moves;
    }

    public void setActivePieces(Color color)
    {
        if (color == Color.WHITE) {
            activePieces = whitePieces;
        } else {
            activePieces = blackPieces;
        }
    }

    public String toString()
    {
        String position = " ";
        for (int i = 0; i < 64; i++) {
            byte square = (byte) ((i + 112) - (24 * (Math.floorDiv(i, 8))));
            position += (board[square] == null ? ". " : String.valueOf(board[square]) + " ");
            if ((i + 1) % 8 == 0) {
                position += "\n ";
            }
        }
        return position;
    }
}
