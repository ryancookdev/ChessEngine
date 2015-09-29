package software.ryancook;

import java.util.*;

public class Engine
{
    private int minDepth;
    private long maxNodes;
    private int minPly;
    private int totalNodes;

    public Engine()
    {
        maxNodes = 1_000_000;
        minDepth = 3;
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    }

    public void setMaxNodes(int maxNodes)
    {
        this.maxNodes = maxNodes;
    }

    public int getTotalNodes()
    {
        return totalNodes;
    }

    public Move calculateBestMove(Board board)
    {
        minPly = board.getPly() + minDepth;
        totalNodes = 0;

        Move bestMove = new Move();
        List<Move> moves = board.getLegalMoves();

        if (board.getColorToMove() == Color.WHITE) {
            bestMove.value = Integer.MIN_VALUE;
        } else {
            bestMove.value = Integer.MAX_VALUE;
        }

        for (Move move : moves) {
            totalNodes++;
            if (board.getColorToMove() == Color.WHITE) {
                Board newBoard = new Board(board);
                boolean currentMoveCapture = isCapture(board, move);
                newBoard.movePiece(move);
                int minValue = min(newBoard, bestMove.value, currentMoveCapture);
                if (bestMove.value < minValue) {
                    bestMove = move;
                    bestMove.value = minValue;
                }
            } else {
                Board newBoard = new Board(board);
                boolean currentMoveCapture = isCapture(board, move);
                newBoard.movePiece(move);
                int maxValue = max(newBoard, bestMove.value, currentMoveCapture);
                if (bestMove.value > maxValue) {
                    bestMove = move;
                    bestMove.value = maxValue;
                }
            }
        }
        return bestMove;
    }

    public int max(Board board, int parentBestValue, boolean lastMoveCapture)
    {
        Move bestMove = getInitialBestMove(board);
        List<Move> moves = getSortedMoves(board);
        for (Move move : moves) {
            incrementAndCheckNodeCount();
            Board newBoard = new Board(board);
            boolean currentMoveCapture = isCapture(board, move);
            boolean quiescent = isQuiescent(lastMoveCapture, currentMoveCapture);
            newBoard.movePiece(move);
            if (quiescent && reachedMinimumDepth(newBoard)) {
                move.value = evaluatePosition(newBoard);
            } else {
                move.value = min(newBoard, bestMove.value, currentMoveCapture);
                // Alpha Beta Pruning
                if (move.value >= parentBestValue) {
                    return move.value;
                }
            }
            if (move.value > bestMove.value) {
                bestMove = move;
            }
        }
        return bestMove.value;
    }

    private int min(Board board, int parentBestValue, boolean lastMoveCapture)
    {
        Move bestMove = getInitialBestMove(board);
        List<Move> moves = getSortedMoves(board);
        for (Move move : moves) {
            incrementAndCheckNodeCount();
            Board newBoard = new Board(board);
            boolean currentMoveCapture = isCapture(board, move);
            boolean quiescent = isQuiescent(lastMoveCapture, currentMoveCapture);
            newBoard.movePiece(move);
            if (quiescent && reachedMinimumDepth(newBoard)) {
                move.value = evaluatePosition(newBoard);
            } else {
                move.value = max(newBoard, bestMove.value, currentMoveCapture);
                // Alpha Beta Pruning
                if (move.value <= parentBestValue) {
                    return move.value;
                }
            }
            if (move.value < bestMove.value) {
                bestMove = move;
            }
        }
        return bestMove.value;
    }

    private List<Move> getSortedMoves(Board board)
    {
        List<Move> moves = board.getLegalMoves();
        Collections.sort(moves, new moveComparator(board));
        return moves;
    }

    private static boolean isCapture(Board board, Move move)
    {
        return (board.getPiece(move.endSquare) != 0);
    }

    private void incrementAndCheckNodeCount()
    {
        totalNodes++;
        assert (totalNodes < maxNodes): "Exceeded max nodes.";
    }

    private boolean reachedMaximumNodes()
    {
        return (totalNodes >= maxNodes);
    }

    private boolean reachedMinimumDepth(Board board)
    {
        return (board.getPly() >= minPly);
    }

    private static boolean isQuiescent(boolean lastMoveCapture, boolean currentMoveCapture)
    {
        return !currentMoveCapture;
    }

    private Move getInitialBestMove(Board board)
    {
        Move bestMove = new Move();
        if (board.getColorToMove() == Color.WHITE) {
            bestMove.value = Integer.MIN_VALUE;
        } else {
            bestMove.value = Integer.MAX_VALUE;
        }
        return bestMove;
    }

    public int evaluatePosition(Board board)
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
        return score;
    }

    private int getPieceValue(byte piece)
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
            byte pieceType1 = (byte) Math.abs(board.getPiece(move1.endSquare));
            byte pieceType2 = (byte) Math.abs(board.getPiece(move2.endSquare));
            return (pieceType1 < pieceType2 ? 1 : -1);
        }
    }
}
