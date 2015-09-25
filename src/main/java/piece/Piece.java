package software.ryancook.piece;

import software.ryancook.Board;
import software.ryancook.Move;
import java.util.List;

public abstract class Piece implements Movement
{
    protected Board board;
    protected byte square;
    protected byte type;
    protected int pieceValue;

    public Piece(Color color)
    {
        type = getType(color);
    }

    public void setLocation(Board board, byte square)
    {
        this.board = board;
        this.square = square;
    }

    public Board getBoard()
    {
        return board;
    }

    protected abstract byte getType(Color color);

    public byte getType()
    {
        return type;
    }

    public byte getSquare()
    {
        return square;
    }

    public void setSquare(byte square)
    {
        this.square = square;
    }

    public abstract List<Move> getLegalMoves();

    public int getValue()
    {
        return pieceValue;
    }

    protected abstract byte[] getPossibleOrientations();

    protected boolean sameColorAsMe(Piece piece)
    {
        assert(piece != null);
        return (type & (1 << 7)) == (piece.type & (1 << 7));
    }

    public String toString()
    {
        if (type == WHITE_PAWN) {
            return "P";
        } else if (type == WHITE_KNIGHT) {
            return "N";
        } else if (type == WHITE_BISHOP) {
            return "B";
        } else if (type == WHITE_ROOK) {
            return "R";
        } else if (type == WHITE_QUEEN) {
            return "Q";
        } else if (type == WHITE_KING) {
            return "K";
        } else if (type == BLACK_PAWN) {
            return "p";
        } else if (type == BLACK_KNIGHT) {
            return "n";
        } else if (type == BLACK_BISHOP) {
            return "b";
        } else if (type == BLACK_ROOK) {
            return "r";
        } else if (type == BLACK_QUEEN) {
            return "q";
        } else if (type == BLACK_KING) {
            return "k";
        }
        return "";
    }
}
