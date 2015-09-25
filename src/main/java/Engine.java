package software.ryancook;

import software.ryancook.piece.Piece;
import java.util.List;

public class Engine
{
    public Move calculateBestMove(Board board)
    {
        Move move = board.getLegalMoves().get(0);
        return move;
    }

    public int evaluatePosition(Board board)
    {
        int score = 0;

        List<Piece> whitePieces = board.getWhitePieces();
        for (Piece piece : whitePieces) {
            score += piece.getValue();
        }

        List<Piece> blackPieces = board.getBlackPieces();
        for (Piece piece : blackPieces) {
            score -= piece.getValue();
        }

        return score;
    }
}
