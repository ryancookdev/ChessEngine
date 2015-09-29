package software.ryancook;

import java.util.*;

public class Board
{
    protected byte[] board;
    // Square, Piece
    private HashMap<Byte, Byte> blackPieces;
    private HashMap<Byte, Byte> whitePieces;
    private HashMap<Byte, Byte> activePieces;
    private int ply;

    public Board()
    {
        board = new byte[128];
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();
        activePieces = null;
        ply = 0;
    }

    public Board(Board oldBoard)
    {
        this();
        for (int i = 0; i < board.length; i++) {
            if (oldBoard.getPiece((byte) i) != 0) {
                setPiece(oldBoard.board[i], (byte) i);
            }
        }
        if (oldBoard.getColorToMove() == Color.WHITE) {
            activePieces = whitePieces;
        } else {
            activePieces = blackPieces;
        }
        ply = oldBoard.getPly();
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
        return board[square];
    }

    public void movePiece(Move move)
    {
        byte piece = board[move.startSquare];
        setPiece(piece, move.endSquare);

        removeFromPieceList(move.startSquare);
        addPieceToBoard(move.startSquare, (byte) 0);

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
        addPieceToBoard(square, piece);
    }

    private void addPieceToBoard(byte square, byte piece)
    {
        board[square] = piece;
    }

    private void removeFromPieceList(byte square)
    {
        byte piece = getPiece(square);
        if (piece < 0) {
            blackPieces.remove(square);;
        } else if (piece > 0) {
            whitePieces.remove(square);;
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
