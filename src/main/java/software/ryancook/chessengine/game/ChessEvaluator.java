package software.ryancook.chessengine.game;

import software.ryancook.gameengine.*;
import java.util.*;

public class ChessEvaluator implements Evaluator
{

    public List<ChessMove> sortMoves(GameState gs, List<ChessMove> moves)
    {
        ChessGameState gameState = (ChessGameState) gs;
        Collections.sort(moves, new moveComparator(gameState));
        return moves;
    }

    public int eval(GameState gameState)
    {
        int score = countMaterial((ChessGameState) gameState);
        score += countActivity((ChessGameState) gameState);
        return score;
    }

    private int countActivity(ChessGameState gameState)
    {
        int activityScore = 0;
        ChessGameState newBoard = new ChessGameState(gameState);
        ChessRuleBook ruleBook = new ChessRuleBook();

        newBoard.setActivePieces(Color.WHITE);
        activityScore += ruleBook.getMoves(newBoard).size();

        newBoard.setActivePieces(Color.BLACK);
        activityScore -= ruleBook.getMoves(newBoard).size();

        return activityScore;
    }

    private int countMaterial(ChessGameState gameState)
    {
        int score = 0;
        HashMap<ChessSquare, ChessPiece> whitePieces = gameState.getWhitePieces();
        Set<ChessSquare> pieces = whitePieces.keySet();
        for (ChessSquare square: pieces) {
            score += getPieceValue(whitePieces.get(square));
        }
        HashMap<ChessSquare, ChessPiece> blackPieces = gameState.getBlackPieces();
        pieces = blackPieces.keySet();
        for (ChessSquare square: pieces) {
            score -= getPieceValue(blackPieces.get(square));
        }
        return score;
    }

    private int getPieceValue(ChessPiece piece)
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

    private class moveComparator implements Comparator<ChessMove>
    {
        ChessGameState gameState;

        public moveComparator(ChessGameState gameState)
        {
            this.gameState = gameState;
        }

        @Override
        public int compare(ChessMove move1, ChessMove move2)
        {
            boolean firstMoveCheck = isCheck(move1);
            boolean secondMoveCheck = isCheck(move2);
            if (firstMoveCheck && !secondMoveCheck) {
                return -1;
            }
            if (!firstMoveCheck && secondMoveCheck) {
                return 1;
            }

            int capturedPiece1 = getPieceValue(gameState.getPiece(move1.getEndSquare()));
            int capturedPiece2 = getPieceValue(gameState.getPiece(move2.getEndSquare()));
            if (capturedPiece1 != capturedPiece2) {
                return capturedPiece2 - capturedPiece1;
            }
            if (move1.getStartSquare() != move2.getStartSquare()) {
                return move1.getStartSquare().getValue() - move2.getStartSquare().getValue();
            }
            return move1.getEndSquare().getValue() - move2.getEndSquare().getValue();
        }

        private boolean isCheck(ChessMove move)
        {
            ChessGameState newGameState = new ChessGameState(gameState);
            newGameState.playMove(move);
            ChessRuleBook ruleBook = new ChessRuleBook();
            return ruleBook.isKingInCheck(newGameState);
        }
    }
}
