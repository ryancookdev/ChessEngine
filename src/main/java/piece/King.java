package software.ryancook.piece;

import software.ryancook.Move;
import software.ryancook.Square;
import java.util.*;

public class King extends Piece
{
    public King(Color color)
    {
        super(color);
    }

    @Override
    protected byte getType(Color color)
    {
        if (color == Color.WHITE) {
            return Movement.WHITE_KING;
        } else {
            return Movement.BLACK_KING;
        }
    }

    /**
     * @TODO Castling
     */
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
        return new byte[] {-17, -16, -15, -1, 1, 15, 16, 17};
    }
}
