public class Piece
{
    public final byte type;
    public final Board board;
    public byte square;

    public static final byte WHITE_PAWN = 1;
    public static final byte WHITE_KNIGHT = 2;
    public static final byte WHITE_KING = 3;
    public static final byte WHITE_BISHOP = 5;
    public static final byte WHITE_ROOK = 6;
    public static final byte WHITE_QUEEN = 7;
    public static final byte BLACK_PAWN = -1;
    public static final byte BLACK_KNIGHT = -2;
    public static final byte BLACK_KING = -3;
    public static final byte BLACK_BISHOP = -5;
    public static final byte BLACK_ROOK = -6;
    public static final byte BLACK_QUEEN = -7;

    public Piece(byte type, Board board, byte square)
    {
        this.type = type;
        this.board = board;
        this.square = square;
    }
}
