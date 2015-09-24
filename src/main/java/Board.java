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
        Piece movedPiece = board[move.startSquare];
        Piece capturedPiece = board[move.endSquare];

        // Update moved piece
        board[move.endSquare] = movedPiece;
        movedPiece.setSquare(move.endSquare);
        board[move.startSquare] = null;

        // Toggle player to move
        activePieces = (activePieces == whitePieces ? blackPieces : whitePieces);

        // Remove captured piece from the piece list
        for (int i = 0; i < activePieces.length; i++) {
            if (activePieces[i] == capturedPiece) {
                activePieces[i] = null;
            }
        }
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

        for (byte i = Square.A8; i <= Square.H8; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }
        position += "\n ";
        for (byte i = Square.A7; i <= Square.H7; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }
        position += "\n ";
        for (byte i = Square.A6; i <= Square.H6; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }
        position += "\n ";
        for (byte i = Square.A5; i <= Square.H5; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }
        position += "\n ";
        for (byte i = Square.A4; i <= Square.H4; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }
        position += "\n ";
        for (byte i = Square.A3; i <= Square.H3; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }
        position += "\n ";
        for (byte i = Square.A2; i <= Square.H2; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }
        position += "\n ";
        for (byte i = Square.A1; i <= Square.H1; i++) {
            position += (board[i] == null ? ". " : String.valueOf(board[i]) + " ");
        }

        return position;
    }
}
