package software.ryancook;

public abstract class Piece implements Movement
{
    public static String getString(byte type)
    {
        if (type == WHITE_PAWN) {
            return "P";
        } else if (type == WHITE_KNIGHT) {
            return "N";
        } else if (type == WHITE_BISHOP) {
            return "B";
        } else if (type == WHITE_ROOK) {
            return "R";
        } else if (type == WHITE_QUEEN) {
            return "Q";
        } else if (type == WHITE_KING) {
            return "K";
        } else if (type == BLACK_PAWN) {
            return "p";
        } else if (type == BLACK_KNIGHT) {
            return "n";
        } else if (type == BLACK_BISHOP) {
            return "b";
        } else if (type == BLACK_ROOK) {
            return "r";
        } else if (type == BLACK_QUEEN) {
            return "q";
        } else if (type == BLACK_KING) {
            return "k";
        }
        return "";
    }
}
