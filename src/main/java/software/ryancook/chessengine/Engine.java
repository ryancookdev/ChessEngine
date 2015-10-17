/*package software.ryancook.engine;

import software.ryancook.game.Color;
import software.ryancook.util.*;
import java.util.*;

public class Engine
{
    private static final int DEFAULT_MAX_TIME = 1000;
    private static final int LOSE_GAME = -99999;
    private static final int WIN_GAME = 99999;
    private final long maxTime;
    private long startTime;
    private int minDepth;
    private int startDepth;

    private ChessEvaluator evaluator;
    private PositionTable positionTable;

    public Engine()
    {
        this.maxTime = DEFAULT_MAX_TIME;
        evaluator = new ChessEvaluator();
        positionTable = new PositionTable();
    }

    public Engine(int maxTime)
    {
        this.maxTime = maxTime;
        evaluator = new ChessEvaluator();
        positionTable = new PositionTable();
    }

    public ChessMove calculateBestMove(ChessBoard board)
    {
        positionTable = new PositionTable();
        startTime();
        ChessMove bestMove = new ChessMove();
        startDepth = board.getPly();
        for (minDepth = 0; minDepth >= 0; minDepth++) {
            boolean discardIteration = false;
            List<ChessMove> moves = getSortedMoves(board);
            int alpha = LOSE_GAME;
            ChessMove bestMoveThisIteration = new ChessMove();
            for (ChessMove move : moves) {
                ChessBoard newBoard = getNewPosition(board, move);

                int score = (-1 * negamax(newBoard, LOSE_GAME, -1 * alpha));

                if (score == WIN_GAME) {
                    System.out.println("I win");
                    return move;
                }

                positionTable.put(newBoard, score, minDepth);

                if (alpha < score) {
                    bestMoveThisIteration = move;
                    alpha = score;
                }
                if (outOfTime()) {
                    discardIteration = true;
                    break;
                }
            }
            if (!discardIteration) {
                bestMove = bestMoveThisIteration;
                System.out.println("Depth: " + minDepth + ", ChessMove: " + bestMove);
            } else {
                System.out.println("Not completed: " + minDepth + ", ChessMove: " + bestMove);
                break;
            }
        }
        if (bestMove.isNull()) {
            System.out.println("You win");
        }
        return bestMove;
    }

    private int negamax(ChessBoard board, int alpha, int beta)
    {
        List<ChessMove> moves = getMoves(board);

        if (reachedMinimumDepth(board) && moves.size() == 0) { // Quiescent
            int score = getSubjectiveScore(board);
            return (score >= beta ? beta : score);
        }
        if (reachedMinimumDepth(board)) {
            addNullMove(moves);
        }

        for (ChessMove move : moves) {
            if (outOfTime()) {
                return beta;
            }
            if (isKingCaptured(board, move)) {
                return WIN_GAME;
            }
            int score = getScore(board, move, alpha, beta);
            if (score >= beta) {
                return beta; // fail hard beta-cutoff
            }
            if (score > alpha) {
                alpha = score;
            }
        }

        if (alpha == LOSE_GAME) {
            if (!ChessRuleBook.isKingInCheck(board)) {
                return 0;
            }
        }

        return alpha;
    }

    private int getScore(ChessBoard board, ChessMove move, int alpha, int beta)
    {
        if (move.isNull()) {
            return getSubjectiveScore(board);
        }

        ChessBoard newBoard = getNewPosition(board, move);

        if (hasPositionAtMinimumDepth(newBoard)) {
            return positionTable.getScore(newBoard);
        }

        if (reachedMinimumDepth(newBoard)) {
            int score = evaluator.eval(newBoard);
            if (score < alpha) { // Delta pruning
                score = alpha;
                positionTable.put(newBoard, score, minDepth);
                return score;
            }
        }

        int score = -negamax(newBoard, -beta, -alpha);
        positionTable.put(newBoard, score, minDepth);
        return score;
    }

    private int getSubjectiveScore(ChessBoard board)
    {
        int score = evaluator.eval(board);
        if (board.getColorToMove() == Color.BLACK) {
            score *= -1;
        }
        return score;
    }

    private void addNullMove(List<ChessMove> moves)
    {
        moves.add(new ChessMove());
    }

    private List<ChessMove> getMoves(ChessBoard board)
    {
        return (reachedMinimumDepth(board) ? getChecksAndCaptures(board) : getSortedMoves(board));
    }

    private boolean hasPositionAtMinimumDepth(ChessBoard board)
    {
        if (positionTable.hasPosition(board)) {
            if (positionTable.getEvaluationDepth(board) >= minDepth) {
                return true;
            }
        }
        return false;
    }

    private void startTime()
    {
        startTime = System.currentTimeMillis();
    }

    private boolean outOfTime()
    {
        return System.currentTimeMillis() - startTime > maxTime;
    }

    private boolean reachedMinimumDepth(ChessBoard board)
    {
        return (board.getPly() > startDepth + minDepth);
    }

    private static boolean isKingCaptured(ChessBoard board, ChessMove move)
    {
        return (board.getPiece(move.getEndSquare()).isKing());
    }

    private static ChessBoard getNewPosition(ChessBoard board, ChessMove move)
    {
        ChessBoard newBoard = new ChessBoard(board);
        newBoard.movePiece(move);
        return newBoard;
    }

    private static boolean isCapture(ChessBoard board, ChessMove move)
    {
        return (board.getPiece(move.getEndSquare()) != ChessPiece.NULL);
    }

    private List<ChessMove> getSortedMoves(ChessBoard board)
    {
        List<ChessMove> validMoves = new ArrayList<>();
        List<ChessMove> moves = ChessRuleBook.getLegalMoves(board);
        for (ChessMove move : moves) {
            ChessBoard newBoard = new ChessBoard(board);
            newBoard.movePiece(move);
            newBoard.toggleColorToMove();
            if (!ChessRuleBook.isKingInCheck(newBoard)) {
                validMoves.add(move);
            }
        }
        evaluator.sortMoves(board, validMoves);
        return validMoves;
    }

    private List<ChessMove> getChecksAndCaptures(ChessBoard board)
    {
        List<ChessMove> moves = getSortedMoves(board);
        for (int i = moves.size() - 1; i >= 0; i--) {
            ChessBoard newBoard = new ChessBoard(board);
            ChessMove move = moves.get(i);
            newBoard.movePiece(move);
            if (isCapture(board, move) || ChessRuleBook.isKingInCheck(newBoard)) {
                return moves;
            }
            moves.remove(i);
        }
        return moves;
    }
}*/
