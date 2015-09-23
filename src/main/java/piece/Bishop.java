package software.ryancook.piece;

public class Bishop extends LongPiece
{
    public Bishop(Color color)
    {
        super(color);
    }

    @Override
    protected byte getType(Color color)
    {
        if (color == Color.WHITE) {
            return Movement.WHITE_BISHOP;
        } else {
            return Movement.BLACK_BISHOP;
        }
    }

    protected byte[] getPossibleOrientations()
    {
        return new byte[] {-17, -15, 15, 17};
    }

}
