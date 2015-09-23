package software.ryancook.piece;

import software.ryancook.Board;

public class Piece implements Movement
{
    public final Board board;
    public byte square;
    public final byte type;

    public Piece(byte type, Board board, byte square)
    {
        this.board = board;
        this.square = square;
        this.type = type;
    }
}
