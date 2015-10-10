package software.ryancook;

import software.ryancook.generics.MultiLevelQueue;
import software.ryancook.util.*;
import java.util.*;

public final class RuleBook
{
    public static List<Move> getLegalMoves(Board board)
    {
        List<Move> moves = new ArrayList<>();
        HashMap<Square, Piece> activePieces = board.getActivePieces();
        Set<Square> pieces = activePieces.keySet();
        for (Square square: pieces) {
            Piece piece = activePieces.get(square);
            if (piece.isKing()) {
                moves.addAll(getLegalKingMoves(board, square));
            } else if (piece.isQueen()) {
                moves.addAll(getLegalQueenMoves(board, square));
            } else if (piece.isRook()) {
                moves.addAll(getLegalRookMoves(board, square));
            } else if (piece.isBishop()) {
                moves.addAll(getLegalBishopMoves(board, square));
            } else if (piece.isKnight()) {
                moves.addAll(getLegalKnightMoves(board, square));
            } else if (piece.isPawn() && piece.isWhite()) {
                moves.addAll(getLegalPawnMoves(board, square, Color.WHITE));
            } else if (piece.isPawn() && piece.isBlack()) {
                moves.addAll(getLegalPawnMoves(board, square, Color.BLACK));
            }
        }
        return moves;
    }

    /**
     * @TODO Castling
     */
    private static List<Move> getLegalKingMoves(Board board, Square square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> squares = square.getKingMoves();
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (sameColorOnBothSquares(board, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    private static List<Move> getLegalQueenMoves(Board board, Square square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> squares = square.getQueenMoves();
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (!squareIsEmpty(board, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(board, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    private static List<Move> getLegalRookMoves(Board board, Square square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> squares = square.getRookMoves();
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (!squareIsEmpty(board, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(board, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    private static List<Move> getLegalBishopMoves(Board board, Square square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> squares = square.getBishopMoves();
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (!squareIsEmpty(board, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(board, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    private static List<Move> getLegalKnightMoves(Board board, Square square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> squares = square.getKnightMoves();
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (sameColorOnBothSquares(board, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    private static List<Move> getLegalPawnMoves(Board board, Square square, Color color)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> squares = square.getPawnMoves(color);
        boolean canAdvance = true;
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (square.sameDiagonal(newSquare)) {
                if (validPawnCapture(board, square, newSquare)) {
                    moves.add(new Move(square, newSquare));
                }
            } else {
                if (canAdvance && squareIsEmpty(board, newSquare)) {
                    moves.add(new Move(square, newSquare));
                } else {
                    canAdvance = false;
                }
            }
        }
        return moves;
    }

    private static boolean validPawnCapture(Board board, Square startSquare, Square captureSquare)
    {
        Piece possiblePiece = board.getPiece(captureSquare);
        if (possiblePiece == Piece.NULL) {
            return false;
        }

        Piece piece = board.getPiece(startSquare);
        if (piece.getColor() == possiblePiece.getColor()) {
            return false;
        }

        return true;
    }

    private static boolean squareIsEmpty(Board board, Square square)
    {
        return board.getPiece(square) == Piece.NULL;
    }

    private static boolean sameColorOnBothSquares(Board board, Square square1, Square square2)
    {
        if (squareIsEmpty(board, square1) || squareIsEmpty(board, square2)) {
            return false;
        }
        Color color1 = board.getPiece(square1).getColor();
        Color color2 = board.getPiece(square2).getColor();
        return (color1 == color2);
    }

    public static boolean isKingInCheck(Board board)
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

}
