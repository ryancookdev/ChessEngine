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
        moves.addAll(getSingleAndDoubleMoves());
        return moves;
    }

    private List<Move> getSingleAndDoubleMoves()
    {
        List<Move> moves = new ArrayList<>();

        byte newSquare = getNextSquare(square);
        if (isSquareOccupied(newSquare)) {
            return moves;
        }
        moves.add(new Move(square, newSquare));

        if (onStartSquare()) {
            newSquare = getNextSquare(newSquare);
            if (isSquareOccupied(newSquare)) {
                return moves;
            }
            moves.add(new Move(square, newSquare));
        }

        return moves;
    }

    private byte getNextSquare(byte currentSquare)
    {
        byte orientation = getPossibleOrientations()[0];
        return (byte) (currentSquare + orientation);
    }

    private boolean isSquareOccupied(byte square)
    {
        Piece possiblePiece = board.getPiece(square);
        if (possiblePiece == null) {
            return false;
        }
        return true;
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
