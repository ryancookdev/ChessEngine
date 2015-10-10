package software.ryancook.engine;

import software.ryancook.*;
import software.ryancook.util.*;
import java.util.*;

public class Evaluator
{

    public List<Move> sortMoves(Board board, List<Move> moves)
    {
        Collections.sort(moves, new moveComparator(board));
        return moves;
    }

    public int evaluate(Board board)
    {
        int score = countMaterial(board);
        score += countActivity(board);
        return score;
    }

    private int countActivity(Board board)
    {
        int activityScore = 0;
        Board newBoard = new Board(board);

        newBoard.setActivePieces(Color.WHITE);
        activityScore += RuleBook.getLegalMoves(newBoard).size();

        newBoard.setActivePieces(Color.BLACK);
        activityScore -= RuleBook.getLegalMoves(newBoard).size();

        return activityScore;
    }

    private int countMaterial(Board board)
    {
        int score = 0;
        HashMap<Square, Piece> whitePieces = board.getWhitePieces();
        Set<Square> pieces = whitePieces.keySet();
        for (Square square: pieces) {
            score += getPieceValue(whitePieces.get(square));
        }
        HashMap<Square, Piece> blackPieces = board.getBlackPieces();
        pieces = blackPieces.keySet();
        for (Square square: pieces) {
            score -= getPieceValue(blackPieces.get(square));
        }
        return score;
    }

    private int getPieceValue(Piece piece)
    {
        if (piece.isKing()) {
            return 99999;
        } else if (piece.isQueen()) {
            return 900;
        } else if (piece.isRook()) {
            return 500;
        } else if (piece.isBishop()) {
            return 300;
        } else if (piece.isKnight()) {
            return 300;
        } else if (piece.isPawn()) {
            return 100;
        }
        return 0;
    }

    private class moveComparator implements Comparator<Move>
    {
        Board board;

        public moveComparator(Board board)
        {
            this.board = board;
        }

        @Override
        public int compare(Move move1, Move move2)
        {
            boolean firstMoveCheck = isCheck(move1);
            boolean secondMoveCheck = isCheck(move2);
            if (firstMoveCheck && !secondMoveCheck) {
                return -1;
            }
            if (!firstMoveCheck && secondMoveCheck) {
                return 1;
            }

            int capturedPiece1 = getPieceValue(board.getPiece(move1.getEndSquare()));
            int capturedPiece2 = getPieceValue(board.getPiece(move2.getEndSquare()));
            if (capturedPiece1 != capturedPiece2) {
                return capturedPiece2 - capturedPiece1;
            }
            if (move1.getStartSquare() != move2.getStartSquare()) {
                return move1.getStartSquare().getValue() - move2.getStartSquare().getValue();
            }
            return move1.getEndSquare().getValue() - move2.getEndSquare().getValue();
        }

        private boolean isCheck(Move move)
        {
            Board newBoard = new Board(board);
            newBoard.movePiece(move);
            return RuleBook.isKingInCheck(newBoard);
        }
    }
}
