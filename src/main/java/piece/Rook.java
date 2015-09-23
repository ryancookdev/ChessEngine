package software.ryancook.piece;

public class Rook extends LongPiece
{
    public Rook(Color color)
    {
        super(color);
    }

    @Override
    protected byte getType(Color color)
    {
        if (color == Color.WHITE) {
            return Movement.WHITE_ROOK;
        } else {
            return Movement.BLACK_ROOK;
        }
    }

    @Override
    protected byte[] getPossibleOrientations()
    {
        return new byte[] {-1, -16, 1, 16};
    }
}
