package software.ryancook.chess.engine;

import software.ryancook.chess.util.*;
import software.ryancook.gameengine.*;
import java.util.*;
import static software.ryancook.gameengine.Negamax.BEST_SCORE;
import static software.ryancook.gameengine.Negamax.WORST_SCORE;

public class ChessEvaluator implements Evaluator
{
    @Override
    public List<Move> sortMoves(final GameState gs, final List<Move> moves)
    {
        final ChessGameState gameState = (ChessGameState) gs;
        Collections.sort(moves, new MoveComparator(gameState));
        return moves;
    }

    public int eval(final GameState gameState)
    {
        final ChessGameState chessGameState = (ChessGameState) gameState;
        if (!whiteHasKing(chessGameState) && blackHasKing(chessGameState)) {
            return WORST_SCORE;
        }
        if (whiteHasKing(chessGameState) && !blackHasKing((ChessGameState) gameState)) {
            return BEST_SCORE;
        }

        final RuleBook ruleBook = new RuleBook();
        if (ruleBook.isMate(chessGameState)) {
            return (gameState.isFirstPlayerToMove() ? WORST_SCORE : BEST_SCORE);
        }
        if (ruleBook.isStalemate(chessGameState)) {
            return 0;
        }

        int score = countMaterial((ChessGameState) gameState);
        score += countActivity((ChessGameState) gameState);
        return score;
    }

    private boolean whiteHasKing(final ChessGameState gameState)
    {
        final HashMap<Square, Piece> whitePieces = gameState.getWhitePieces();
        final Set<Square> pieces = whitePieces.keySet();
        for (final Square square: pieces) {
            if (getPieceValue(whitePieces.get(square)) == 99999) {
                return true;
            }
        }
        return false;
    }

    private boolean blackHasKing(final ChessGameState gameState)
    {
        final HashMap<Square, Piece> blackPieces = gameState.getBlackPieces();
        final Set<Square> pieces = blackPieces.keySet();
        for (final Square square: pieces) {
            if (getPieceValue(blackPieces.get(square)) == 99999) {
                return true;
            }
        }
        return false;
    }

    private int countActivity(final ChessGameState gameState)
    {
        int activityScore = 0;
        final RuleBook ruleBook = new RuleBook();
        ChessGameState.Builder builder = gameState.getBuilder();

        builder.setActivePieces(Color.WHITE);
        ChessGameState newBoard = builder.build();
        activityScore += ruleBook.getMoves(newBoard).size();

        builder.setActivePieces(Color.BLACK);
        newBoard = builder.build();
        activityScore -= ruleBook.getMoves(newBoard).size();

        return activityScore;
    }

    private int countMaterial(final ChessGameState gameState)
    {
        int score = 0;
        final HashMap<Square, Piece> whitePieces = gameState.getWhitePieces();
        Set<Square> pieces = whitePieces.keySet();
        for (final Square square: pieces) {
            score += getPieceValue(whitePieces.get(square));
        }
        final HashMap<Square, Piece> blackPieces = gameState.getBlackPieces();
        pieces = blackPieces.keySet();
        for (final Square square: pieces) {
            score -= getPieceValue(blackPieces.get(square));
        }
        return score;
    }

    private int getPieceValue(final Piece piece)
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

    private class MoveComparator implements Comparator<Move>
    {
        ChessGameState gameState;

        public MoveComparator(final ChessGameState gameState)
        {
            this.gameState = gameState;
        }

        @Override
        public int compare(final Move m1, final Move m2)
        {
            final ChessMove move1 = (ChessMove) m1;
            final ChessMove move2 = (ChessMove) m2;

            final boolean firstMoveCheck = isCheck(move1);
            final boolean secondMoveCheck = isCheck(move2);
            if (firstMoveCheck && !secondMoveCheck) {
                return -1;
            }
            if (!firstMoveCheck && secondMoveCheck) {
                return 1;
            }

            final int capturedPiece1 = getPieceValue(gameState.getPiece(move1.getEndSquare()));
            final int capturedPiece2 = getPieceValue(gameState.getPiece(move2.getEndSquare()));
            if (capturedPiece1 != capturedPiece2) {
                return capturedPiece2 - capturedPiece1;
            }
            if (move1.getStartSquare() != move2.getStartSquare()) {
                return move1.getStartSquare().getValue() - move2.getStartSquare().getValue();
            }
            return move1.getEndSquare().getValue() - move2.getEndSquare().getValue();
        }

        private boolean isCheck(final ChessMove move)
        {
            ChessGameState newGameState = new ChessGameState(gameState);
            newGameState = (ChessGameState) newGameState.playMove(move);
            final RuleBook ruleBook = new RuleBook();
            return ruleBook.isCheck(newGameState);
        }
    }
}
