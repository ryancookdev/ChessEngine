package software.ryancook.piece;

public class Queen extends LongPiece
{
    public Queen(Color color)
    {
        super(color);
        pieceValue = 9;
    }

    @Override
    protected byte getType(Color color)
    {
        if (color == Color.WHITE) {
            return Movement.WHITE_QUEEN;
        } else {
            return Movement.BLACK_QUEEN;
        }
    }

    @Override
    public int getValue()
    {
        return pieceValue;
    }

    @Override
    protected byte[] getPossibleOrientations()
    {
        return new byte[] {-17, -16, -15, -1, 1, 15, 16, 17};
    }
}
