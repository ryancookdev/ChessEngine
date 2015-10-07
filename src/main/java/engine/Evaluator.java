package software.ryancook.engine;

import software.ryancook.Board;
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
        if (board.getColorToMove() == Color.WHITE) {
            score *= -1;
        }
        return score;
    }

    private int getPieceValue(Piece piece)
    {
        if (piece.isKing()) {
            return 999;
        } else if (piece.isQueen()) {
            return 9;
        } else if (piece.isRook()) {
            return 5;
        } else if (piece.isBishop()) {
            return 3;
        } else if (piece.isKnight()) {
            return 3;
        } else if (piece.isPawn()) {
            return 1;
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
            int pieceValue1 = getPieceValue(board.getPiece(move1.getEndSquare()));
            int pieceValue2 = getPieceValue(board.getPiece(move2.getEndSquare()));
            return pieceValue2 - pieceValue1;
        }
    }
}
