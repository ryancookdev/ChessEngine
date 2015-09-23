package software.ryancook.piece;

import software.ryancook.Move;
import software.ryancook.Square;

import java.util.*;

public class Knight extends Piece
{
    public Knight(Color color)
    {
        super(color);
    }

    @Override
    protected byte getType(Color color)
    {
        if (color == Color.WHITE) {
            return Movement.WHITE_KNIGHT;
        } else {
            return Movement.BLACK_KNIGHT;
        }
    }

    public List<Move> getLegalMoves()
    {
        List<Move> moves = new ArrayList<>();
        byte[] orientations = getPossibleOrientations();
        for (byte orientation : orientations) {
            byte newSquare = (byte) (square + orientation);
            if (!Square.isValid(newSquare)) {
                continue;
            }
            Piece possiblePiece = board.getPiece(newSquare);
            if (possiblePiece != null && sameColorAsMe(possiblePiece)) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    @Override
    protected byte[] getPossibleOrientations()
    {
        return new byte[] {-33, -31, -18, -14, 14, 18, 31, 33};
    }
}
