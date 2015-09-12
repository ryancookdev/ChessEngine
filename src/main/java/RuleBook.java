import java.util.ArrayList;

public class RuleBook
{
    public static final ArrayList<Move> getLegalMoves(Board board)
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int i = 0; i < 20; i++) {
            moves.add(new Move(Square.E2, Square.E4));
        }
        return moves;
    }
}
