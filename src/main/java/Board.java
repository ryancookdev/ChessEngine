package software.ryancook;

import java.util.*;

public class Board
{
    // Square, Piece
    private HashMap<Byte, Byte> blackPieces;
    private HashMap<Byte, Byte> whitePieces;
    private HashMap<Byte, Byte> activePieces;
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

    private void copyPieceList(HashMap<Byte, Byte> oldPieceList)
    {
        Set<Byte> pieces = oldPieceList.keySet();
        for (byte square: pieces) {
            byte piece = oldPieceList.get(square);
            if (piece != 0) {
                setPiece(piece, square);
            }
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

    public byte getPiece(byte square)
    {
        if (whitePieces.containsKey(square)) {
            return whitePieces.get(square);
        }
        if (blackPieces.containsKey(square)) {
            return blackPieces.get(square);
        }
        return (byte) 0;
    }

    public void movePiece(Move move)
    {
        byte piece = getPiece(move.startSquare);
        setPiece(piece, move.endSquare);
        removeFromPieceList(move.startSquare);
        togglePlayerToMove();
        ply++;
    }

    private void togglePlayerToMove()
    {
        activePieces = (activePieces == whitePieces ? blackPieces : whitePieces);
    }

    public void setPiece(byte piece, byte square)
    {
        removeFromPieceList(square);
        addToPieceList(square, piece);
    }

    private void removeFromPieceList(byte square)
    {
        byte piece = getPiece(square);
        if (piece < 0) {
            blackPieces.remove(square);
        } else if (piece > 0) {
            whitePieces.remove(square);
        }
    }

    public void addToPieceList(byte square, byte piece)
    {
        if (piece < 0) {
            blackPieces.put(square, piece);
        } else if (piece > 0) {
            whitePieces.put(square, piece);
        }
    }

    public HashMap<Byte, Byte> getWhitePieces()
    {
        return whitePieces;
    }

    public HashMap<Byte, Byte> getBlackPieces()
    {
        return blackPieces;
    }

    public HashMap<Byte,Byte> getActivePieces()
    {
        return activePieces;
    }

    public int getTotalWhitePieces()
    {
        int total = 0;
        Set<Byte> pieces = whitePieces.keySet();
        for (byte square: pieces) {
            if (whitePieces.get(square) != 0) {
                total++;
            }
        }
        return total;
    }

    public int getTotalBlackPieces()
    {
        int total = 0;
        Set<Byte> pieces = blackPieces.keySet();
        for (byte square: pieces) {
            if (blackPieces.get(square) != 0) {
                total++;
            }
        }
        return total;
    }

    public List<Move> getLegalMoves()
    {
        return RuleBook.getLegalMoves(this);
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
        byte[] board = new byte[128];
        Set<Byte> pieces = blackPieces.keySet();
        for (byte square: pieces) {
            board[square] = blackPieces.get(square);
        }
        pieces = whitePieces.keySet();
        for (byte square: pieces) {
            board[square] = whitePieces.get(square);
        }
        String position = " ";
        for (int i = 0; i < 64; i++) {
            byte square = (byte) ((i + 112) - (24 * (Math.floorDiv(i, 8))));
            position += (board[square] == 0 ? ". " : Piece.getString(board[square]) + " ");
            if ((i + 1) % 8 == 0) {
                position += "\n ";
            }
        }
        return position;
    }
}
