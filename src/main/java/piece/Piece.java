package software.ryancook.piece;

import software.ryancook.Board;
import software.ryancook.Move;
import java.util.List;

public abstract class Piece implements Movement
{
    protected Board board;
    protected byte square;
    protected byte type;

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

    protected abstract byte[] getPossibleOrientations();

    protected boolean sameColorAsMe(Piece piece)
    {
        assert(piece != null);
        return (type & (1 << 7)) == (piece.type & (1 << 7));
    }
}
