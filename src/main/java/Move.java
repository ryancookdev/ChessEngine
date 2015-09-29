package software.ryancook;

public class Move
{
    public final byte startSquare;
    public final byte endSquare;
    public int value;

    public Move(byte startSquare, byte endSquare)
    {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        value = 0;
    }

    public Move()
    {
        startSquare = -1;
        endSquare = -1;
        value = 0;
    }

    public String toString()
    {
        return Square.getString(startSquare) + "-" + Square.getString(endSquare);
    }
}
