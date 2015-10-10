package software.ryancook.util;

public class Move
{
    private final Square startSquare;
    private final Square endSquare;

    public Move()
    {
        startSquare = Square.NULL;
        endSquare = Square.NULL;
    }

    public Move(Square startSquare, Square endSquare)
    {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }

    public Square getStartSquare()
    {
        return startSquare;
    }

    public Square getEndSquare()
    {
        return endSquare;
    }

    public boolean isNull()
    {
        return (startSquare == Square.NULL || endSquare == Square.NULL);
    }

    @Override
    public String toString()
    {
        return startSquare.toString() + "-" + endSquare.toString();
    }
}
