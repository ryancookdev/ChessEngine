package software.ryancook;

import java.util.List;

public interface Movement
{
    byte PAWN = 1;
    byte KNIGHT = 2;
    byte KING = 3;
    byte BISHOP = 5;
    byte ROOK = 6;
    byte QUEEN = 7;
    byte WHITE_PAWN = 1;
    byte WHITE_KNIGHT = 2;
    byte WHITE_KING = 3;
    byte WHITE_BISHOP = 5;
    byte WHITE_ROOK = 6;
    byte WHITE_QUEEN = 7;
    byte BLACK_PAWN = -1;
    byte BLACK_KNIGHT = -2;
    byte BLACK_KING = -3;
    byte BLACK_BISHOP = -5;
    byte BLACK_ROOK = -6;
    byte BLACK_QUEEN = -7;

    List<Move> getLegalMoves();
}

