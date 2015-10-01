package software.ryancook;

import java.util.*;

public class Engine
{
    private static final int LOSE_GAME = -999;
    private static final int WIN_GAME = 999;
    private final int minDepth;
    private final long maxNodes;
    private int minPly;
    private long maxNodesPerMove;
    private int totalNodes;

    public Engine()
    {
        this.minDepth = 4;
        this.maxNodes = 5000000;
    }

    public Engine(int minDepth, int maxNodes)
    {
        this.minDepth = minDepth;
        this.maxNodes = maxNodes;
    }

    public Move calculateBestMove(Board board)
    {
        setMinPly(board);
        List<Move> moves = getSortedMoves(board);
        maxNodesPerMove = Math.floorDiv(maxNodes, moves.size());
        Move bestMove = new Move();
        bestMove.value = LOSE_GAME;
        for (Move move : moves) {
            totalNodes = 1;
            Board newBoard = getNewPosition(board, move);
            if (true) {
                int score = (-1 * negamax(newBoard, LOSE_GAME, -1 * bestMove.value, false));
                if (bestMove.value < score) {
                    bestMove = move;
                    bestMove.value = score;
                }
            }
        }
        return bestMove;
    }

    private int negamax(Board board, int alpha, int beta, boolean lastMoveCapture)
    {
        List<Move> moves = getSortedMoves(board);
        for (Move move : moves) {
            if (!incrementAndCheckNodeCount()) {
                return beta;
            }
            boolean capture = isCapture(board, move);
            if (isKingCaptured(board, move)) {
                return WIN_GAME;
            }
            Board newBoard = getNewPosition(board, move);
            int score;
            if (reachedMinimumDepth(newBoard) && isQuiescent(newBoard, alpha, capture, lastMoveCapture)) {
                score = evaluatePosition(newBoard);
            } else {
                score = (-1 * negamax(newBoard, -1 * beta, -1 * alpha, capture));
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

    public int getTotalNodes()
    {
        return totalNodes;
    }

    public List<Move> getSortedMoves(Board board)
    {
        List<Move> moves = board.getLegalMoves();
        Collections.sort(moves, new moveComparator(board));
        return moves;
    }

    private void setMinPly(Board board)
    {
        minPly = board.getPly() + minDepth;
    }

    private boolean incrementAndCheckNodeCount()
    {
        return (++totalNodes < maxNodesPerMove);
    }

    private boolean reachedMaximumNodes()
    {
        return (totalNodes >= maxNodes);
    }

    private boolean reachedMinimumDepth(Board board)
    {
        return (board.getPly() >= minPly);
    }

    private static boolean isKingInCheck(Board board)
    {
        Board newBoard = new Board(board);
        playNullMove(newBoard);
        List<Move> moves = newBoard.getLegalMoves();
        for (Move move : moves) {
            if (Math.abs(newBoard.getPiece(move.endSquare)) == Piece.KING) {
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
        return (Math.abs(board.getPiece(move.endSquare)) == Piece.KING);
    }

    private static Board getNewPosition(Board board, Move move)
    {
        Board newBoard = new Board(board);
        newBoard.movePiece(move);
        return newBoard;
    }

    private static boolean isCapture(Board board, Move move)
    {
        return (board.getPiece(move.endSquare) != 0);
    }

    private static boolean isQuiescent(Board board, int alpha, boolean capture, boolean lastMoveCapture)
    {
        if (!lastMoveCapture && !capture) {
            return true;
        }
        if (eligibleForDeltaPruning(board, alpha, capture)) {
            return true;
        }
        return false;
    }

    private static boolean eligibleForDeltaPruning(Board board, int alpha, boolean capture)
    {
        return (capture && evaluatePosition(board) >= alpha);
    }

    public static int evaluatePosition(Board board)
    {
        int score = 0;
        HashMap<Byte, Byte> whitePieces = board.getWhitePieces();
        Set<Byte> pieces = whitePieces.keySet();
        for (byte square: pieces) {
            score += getPieceValue(whitePieces.get(square));
        }
        HashMap<Byte, Byte> blackPieces = board.getBlackPieces();
        pieces = blackPieces.keySet();
        for (byte square: pieces) {
            score -= getPieceValue(blackPieces.get(square));
        }
        if (board.getColorToMove() == Color.WHITE) {
            score *= -1;
        }
        return score;
    }

    private static int getPieceValue(byte piece)
    {
        if (Math.abs(piece) == Piece.KING) {
            return 999;
        } else if (Math.abs(piece) == Piece.QUEEN) {
            return 9;
        } else if (Math.abs(piece) == Piece.ROOK) {
            return 5;
        } else if (Math.abs(piece) == Piece.BISHOP) {
            return 3;
        } else if (Math.abs(piece) == Piece.KNIGHT) {
            return 3;
        } else if (Math.abs(piece) == Piece.PAWN) {
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
            int pieceValue1 = getPieceValue(board.getPiece(move1.endSquare));
            int pieceValue2 = getPieceValue(board.getPiece(move2.endSquare));
            return pieceValue2 - pieceValue1;
        }
    }
}
