package software.ryancook.piece;

import software.ryancook.Move;
import software.ryancook.Square;
import java.util.*;

public class Pawn extends Piece
{
    public Pawn(Color color)
    {
        super(color);
    }

    @Override
    protected byte getType(Color color)
    {
        if (color == Color.WHITE) {
            return Movement.WHITE_PAWN;
        } else {
            return Movement.BLACK_PAWN;
        }
    }

    public List<Move> getLegalMoves()
    {
        List<Move> moves = new ArrayList<>();
        moves.addAll(getSingleMoves());
        return moves;
    }

    private List<Move> getSingleMoves()
    {
        List<Move> moves = new ArrayList<>();
        byte orientation = getPossibleOrientations()[0];
        byte newSquare = (byte) (square + orientation);
        if (!Square.isValid(newSquare)) {
            return moves;
        }
        Piece possiblePiece = board.getPiece(newSquare);
        if (possiblePiece != null) {
            return moves;
        }
        moves.add(new Move(square, newSquare));
        return moves;
    }

    private List<Move> getDoubleMoves()
    {
        List<Move> moves = new ArrayList<>();

        if (!onStartSquare()) {
            return moves;
        }

        /*byte orientation = getPossibleOrientations()[0];
        byte newSquare = (byte) (square + orientation);
        if (!Square.isValid(newSquare)) {
            return moves;
        }
        Piece possiblePiece = board.getPiece(newSquare);
        if (possiblePiece != null) {
            return moves;
        }
        moves.add(new Move(square, newSquare));*/
        return moves;
    }

    private boolean onStartSquare()
    {
        if (type == WHITE_PAWN && Square.getRank(square) == 2) {
            return true;
        }
        if (type == BLACK_PAWN && Square.getRank(square) == 7) {
            return true;
        }
        return false;
    }

    @Override
    protected byte[] getPossibleOrientations()
    {
        if (type == WHITE_PAWN) {
            return new byte[]{16};
        } else if (type == BLACK_PAWN){
            return new byte[]{-16};
        }
        return new byte[0];
    }
}
