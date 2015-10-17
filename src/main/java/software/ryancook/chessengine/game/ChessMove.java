package software.ryancook.chessengine.game;

import software.ryancook.gameengine.Move;

public class ChessMove implements Move
{
    private final ChessSquare startSquare;
    private final ChessSquare endSquare;

    public ChessMove()
    {
        startSquare = ChessSquare.NULL;
        endSquare = ChessSquare.NULL;
    }

    public ChessMove(ChessSquare startSquare, ChessSquare endSquare)
    {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }

    public ChessSquare getStartSquare()
    {
        return startSquare;
    }

    public ChessSquare getEndSquare()
    {
        return endSquare;
    }

    public boolean isNull()
    {
        return (startSquare == ChessSquare.NULL || endSquare == ChessSquare.NULL);
    }

    @Override
    public String toString()
    {
        return startSquare.toString() + "-" + endSquare.toString();
    }
}
