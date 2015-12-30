package software.ryancook.chess.engine;

import software.ryancook.chess.util.Square;
import software.ryancook.gameengine.Move;

public class ChessMove implements Move
{
    private final Square startSquare;
    private final Square endSquare;

    public ChessMove()
    {
        startSquare = Square.NULL;
        endSquare = Square.NULL;
    }

    public ChessMove(final Square startSquare, final Square endSquare)
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
