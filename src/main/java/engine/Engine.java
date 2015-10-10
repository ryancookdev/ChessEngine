package software.ryancook.engine;

import software.ryancook.*;
import software.ryancook.util.*;
import java.util.*;

public class Engine
{
    private static final int DEFAULT_MAX_TIME = 1000;
    private static final int LOSE_GAME = -99999;
    private static final int WIN_GAME = 99999;
    private final long maxTime;
    private int minDepth;
    private int minPly;
    private long startTime;

    private Evaluator evaluator;
    private PositionTable positionTable;

    public Engine()
    {
        this.maxTime = DEFAULT_MAX_TIME;
        evaluator = new Evaluator();
        positionTable = new PositionTable();
    }

    public Engine(int maxTime)
    {
        this.maxTime = maxTime;
        evaluator = new Evaluator();
        positionTable = new PositionTable();
    }

    public Move calculateBestMove(Board board)
    {
        positionTable = new PositionTable();
        startTime();
        Move bestMove = new Move();
        for (minDepth = 0; minDepth >= 0; minDepth++) {
            boolean discardIteration = false;
            setMinPly(board);
            List<Move> moves = getSortedMoves(board);
            int alpha = LOSE_GAME;
            Move bestMoveThisIteration = new Move();
            for (Move move : moves) {
                Board newBoard = getNewPosition(board, move);

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
                System.out.println("Depth: " + minDepth + ", Move: " + bestMove);
            } else {
                System.out.println("Not completed: " + minDepth + ", Move: " + bestMove);
                break;
            }
        }
        if (bestMove.isNull()) {
            System.out.println("You win");
        }
        return bestMove;
    }

    private int negamax(Board board, int alpha, int beta)
    {
        List<Move> moves;

        if (reachedMinimumDepth(board)) {
            moves = getChecksAndCaptures(board);
            if (moves.size() == 0) {
                // Quiescent
                int score = evaluator.evaluate(board);
                if (board.getColorToMove() == Color.BLACK) {
                    score *= -1;
                }
                if (score >= beta) {
                    return beta; // fail hard beta-cutoff
                }
                return score;
            }
            moves.add(new Move());
        } else {
            moves = getSortedMoves(board);
        }

        for (Move move : moves) {
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
            if (!RuleBook.isKingInCheck(board)) {
                return 0;
            }
        }

        return alpha;
    }

    private int getScore(Board board, Move move, int alpha, int beta)
    {
        if (move.isNull()) {
            int score = evaluator.evaluate(board);
            if (board.getColorToMove() == Color.BLACK) {
                score *= -1;
            }
            return score;
        }

        Board newBoard = getNewPosition(board, move);

        if (hasPositionAtMinimumDepth(newBoard)) {
            return positionTable.getScore(newBoard);
        }

        int score;
        if (reachedMinimumDepth(newBoard)) {
            score = evaluator.evaluate(newBoard);
            if (score < alpha) { // Delta pruning
                score = alpha;
                positionTable.put(newBoard, score, minDepth);
                return score;
            }
        }

        score = (-1 * negamax(newBoard, -1 * beta, -1 * alpha));
        positionTable.put(newBoard, score, minDepth);
        return score;
    }

    private boolean hasPositionAtMinimumDepth(Board board)
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

    private void setMinPly(Board board)
    {
        minPly = board.getPly() + minDepth;
    }

    private boolean outOfTime()
    {
        return System.currentTimeMillis() - startTime > maxTime;
    }

    private boolean reachedMinimumDepth(Board board)
    {
        return (board.getPly() > minPly);
    }

    private static boolean isKingCaptured(Board board, Move move)
    {
        return (board.getPiece(move.getEndSquare()).isKing());
    }

    private static Board getNewPosition(Board board, Move move)
    {
        Board newBoard = new Board(board);
        newBoard.movePiece(move);
        return newBoard;
    }

    private static boolean isCapture(Board board, Move move)
    {
        return (board.getPiece(move.getEndSquare()) != Piece.NULL);
    }

    private List<Move> getSortedMoves(Board board)
    {
        List<Move> validMoves = new ArrayList<>();
        List<Move> moves = RuleBook.getLegalMoves(board);
        for (Move move : moves) {
            Board newBoard = new Board(board);
            newBoard.movePiece(move);
            newBoard.toggleColorToMove();
            if (!RuleBook.isKingInCheck(newBoard)) {
                validMoves.add(move);
            }
        }
        evaluator.sortMoves(board, validMoves);
        return validMoves;
    }

    private List<Move> getChecksAndCaptures(Board board)
    {
        List<Move> moves = getSortedMoves(board);
        for (int i = moves.size() - 1; i >= 0; i--) {
            Board newBoard = new Board(board);
            Move move = moves.get(i);
            newBoard.movePiece(move);
            if (isCapture(board, move) || RuleBook.isKingInCheck(newBoard)) {
                return moves;
            }
            moves.remove(i);
        }
        return moves;
    }
}
