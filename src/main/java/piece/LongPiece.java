package software.ryancook.piece;

import software.ryancook.Move;
import software.ryancook.Square;
import java.util.*;

public abstract class LongPiece extends Piece
{
    public LongPiece(Color color)
    {
        super(color);
    }

    public List<Move> getLegalMoves()
    {
        List<Move> moves = new ArrayList<>();
        byte[] orientations = getPossibleOrientations();
        for (byte orientation : orientations) {
            for (int j = 1; j < 8; j++) {
                byte newSquare = (byte) (square + (j * orientation));
                if (!Square.isValid(newSquare)) {
                    break;
                }
                Piece possiblePiece = board.getPiece(newSquare);
                if (possiblePiece != null && sameColorAsMe(possiblePiece)) {
                    break;
                }
                moves.add(new Move(square, newSquare));
                if (possiblePiece != null && !sameColorAsMe(possiblePiece)) {
                    break;
                }
            }
        }
        return moves;
    }

    protected abstract byte[] getPossibleOrientations();
}
