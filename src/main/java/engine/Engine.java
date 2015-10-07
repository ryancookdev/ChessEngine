package software.ryancook.engine;

import software.ryancook.*;
import software.ryancook.util.*;
import java.util.*;

public class Engine
{
    private static final int DEFAULT_MAX_TIME = 1000;
    private static final int LOSE_GAME = -999;
    private static final int WIN_GAME = 999;
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

                int score = (-1 * negamax(newBoard, LOSE_GAME, -1 * alpha, false));
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
        return bestMove;
    }

    private int negamax(Board board, int alpha, int beta, boolean lastMoveCapture)
    {
        List<Move> moves;
        if (reachedMinimumDepth(board)) {
            moves = getSortedCaptures(board);
        } else {
            moves = getSortedMoves(board);
        }
        for (Move move : moves) {
            if (outOfTime()) {
                return beta;
            }
            boolean capture = isCapture(board, move);
            if (isKingCaptured(board, move)) {
                return WIN_GAME;
            }
            Board newBoard = getNewPosition(board, move);

            int score;
            if (hasPositionAtMinimumDepth(newBoard)) {
                score = positionTable.getScore(newBoard);
            } else {
                if (reachedMinimumDepth(newBoard) && isQuiescent(newBoard, alpha, capture, lastMoveCapture)) {
                    score = evaluator.evaluate(newBoard);
                } else if (reachedMinimumDepth(newBoard) && eligibleForDeltaPruning(newBoard, alpha, capture)) {
                    // This seems to lead to bad queen sacrifices
                    score = alpha;
                } else {
                    score = (-1 * negamax(newBoard, -1 * beta, -1 * alpha, capture));
                }
                positionTable.put(newBoard, score, minDepth);
            }
            if (score >= beta) {
                return beta; // fail hard beta-cutoff
            }
            if (score > alpha) {
                alpha = score;
            }
        }
        if (alpha == LOSE_GAME) {
            if (!isKingInCheck(board)) {
                return 0;
            }
        }
        return alpha;
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
        return (board.getPly() >= minPly);
    }

    private static boolean isKingInCheck(Board board)
    {
        Board newBoard = new Board(board);
        playNullMove(newBoard);
        List<Move> moves = RuleBook.getLegalMoves(newBoard);
        for (Move move : moves) {
            if (newBoard.getPiece(move.getEndSquare()).isKing()) {
                return true;
            }
        }
        return false;
    }

    private static void playNullMove(Board board)
    {
        Color otherColor = (board.getColorToMove() == Color.WHITE ? Color.BLACK : Color.WHITE);
        board.setActivePieces(otherColor);
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

    private boolean isQuiescent(Board board, int alpha, boolean capture, boolean lastMoveCapture)
    {
        if (!lastMoveCapture && !capture) {
            return true;
        }
        return false;
    }

    private boolean eligibleForDeltaPruning(Board board, int alpha, boolean capture)
    {
        return (capture && evaluator.evaluate(board) >= alpha);
    }

    private List<Move> getSortedMoves(Board board)
    {
        List<Move> moves = RuleBook.getLegalMoves(board);
        evaluator.sortMoves(board, moves);
        return moves;
    }

    private List<Move> getSortedCaptures(Board board)
    {
        List<Move> captures = new ArrayList<>();
        List<Move> moves = getSortedMoves(board);
        for (Move move: moves) {
            if (board.getPiece(move.getEndSquare()) != Piece.NULL) {
                captures.add(move);
            }
        }
        return moves;
    }

}
