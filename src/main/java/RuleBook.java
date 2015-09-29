package software.ryancook;

import java.util.*;

public class RuleBook
{
    private final static byte[] blackPawnOrientation = new byte[] {-16};
    private final static byte[] whitePawnOrientation = new byte[] {16};
    private final static byte[] knightOrientation = new byte[] {-33, -31, -18, -14, 14, 18, 31, 33};
    private final static byte[] bishopOrientation = new byte[]{-17, -15, 15, 17};
    private final static byte[] rookOrientation = new byte[] {-1, -16, 1, 16};
    private final static byte[] queenOrientation = new byte[] {-17, -16, -15, -1, 1, 15, 16, 17};
    private final static byte[] kingOrientation = new byte[] {-17, -16, -15, -1, 1, 15, 16, 17};

    public static List<Move> getLegalMoves(Board board)
    {
        List<Move> moves = new ArrayList<>();
        HashMap<Byte, Byte> activePieces = board.getActivePieces();
        Set<Byte> pieces = activePieces.keySet();
        for (byte square: pieces) {
            byte piece = activePieces.get(square);
            if (Math.abs(piece) == Piece.KING) {
                moves.addAll(getLegalKingMoves(board, square));
            } else if (Math.abs(piece) == Piece.QUEEN) {
                moves.addAll(getLegalQueenMoves(board, square));
            } else if (Math.abs(piece) == Piece.ROOK) {
                moves.addAll(getLegalRookMoves(board, square));
            } else if (Math.abs(piece) == Piece.BISHOP) {
                moves.addAll(getLegalBishopMoves(board, square));
            } else if (Math.abs(piece) == Piece.KNIGHT) {
                moves.addAll(getLegalKnightMoves(board, square));
            } else if (piece == Piece.WHITE_PAWN) {
                moves.addAll(getLegalPawnMoves(board, square, Color.WHITE));
            } else if (piece == Piece.BLACK_PAWN) {
                moves.addAll(getLegalPawnMoves(board, square, Color.BLACK));
            }
        }
        return moves;
    }

    private static List<Move> getLegalKnightMoves(Board board, byte square)
    {
        List<Move> moves = new ArrayList<>();
        for (byte orientation : knightOrientation) {
            byte newSquare = (byte) (square + orientation);
            if (!Square.isValid(newSquare)) {
                continue;
            }
            byte piece = board.getPiece(square);
            byte possiblePiece = board.getPiece(newSquare);
            Color pieceColor = (piece < 0 ? Color.BLACK : Color.WHITE);
            Color possiblePieceColor = (possiblePiece < 0 ? Color.BLACK : Color.WHITE);
            if (possiblePiece != 0 && pieceColor == possiblePieceColor) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    /**
     * @TODO Castling
     */
    private static List<Move> getLegalKingMoves(Board board, byte square)
    {
        List<Move> moves = new ArrayList<>();
        for (byte orientation : kingOrientation) {
            byte newSquare = (byte) (square + orientation);
            if (!Square.isValid(newSquare)) {
                continue;
            }
            byte piece = board.getPiece(square);
            byte possiblePiece = board.getPiece(newSquare);
            Color pieceColor = (piece < 0 ? Color.BLACK : Color.WHITE);
            Color possiblePieceColor = (possiblePiece < 0 ? Color.BLACK : Color.WHITE);
            if (possiblePiece != 0 && pieceColor == possiblePieceColor) {
                continue;
            }
            moves.add(new Move(square, newSquare));
        }
        return moves;
    }

    private static List<Move> getLegalBishopMoves(Board board, byte square)
    {
        return getLegalLongPieceMoves(board, square, bishopOrientation);
    }

    private static List<Move> getLegalRookMoves(Board board, byte square)
    {
        return getLegalLongPieceMoves(board, square, rookOrientation);
    }

    private static List<Move> getLegalQueenMoves(Board board, byte square)
    {
        return getLegalLongPieceMoves(board, square, queenOrientation);
    }

    private static List<Move> getLegalLongPieceMoves(Board board, byte square, byte[] orientations)
    {
        List<Move> moves = new ArrayList<>();
        for (byte orientation : orientations) {
            for (int j = 1; j < 8; j++) {
                byte newSquare = (byte) (square + (j * orientation));
                if (!Square.isValid(newSquare)) {
                    break;
                }
                byte piece = board.getPiece(square);
                byte possiblePiece = board.getPiece(newSquare);
                Color pieceColor = (piece < 0 ? Color.BLACK : Color.WHITE);
                Color possiblePieceColor = (possiblePiece < 0 ? Color.BLACK : Color.WHITE);
                if (possiblePiece != 0 && pieceColor == possiblePieceColor) {
                    break;
                }
                moves.add(new Move(square, newSquare));
                if (possiblePiece != 0 && pieceColor != possiblePieceColor) {
                    break;
                }
            }
        }
        return moves;
    }

    public static List<Move> getLegalPawnMoves(Board board, byte square, Color color)
    {
        List<Move> moves = new ArrayList<>();
        moves.addAll(getSingleAndDoubleMoves(board, color, square));
        moves.addAll(getCaptures(board, color, square));
        return moves;
    }

    private static List<Move> getSingleAndDoubleMoves(Board board, Color color, byte square)
    {
        List<Move> moves = new ArrayList<>();
        byte newSquare = getNextSquare(color, square);
        if (!Square.isValid(newSquare)) {
            return moves;
        }
        if (board.getPiece(newSquare) != 0) {
            return moves;
        }
        moves.add(new Move(square, newSquare));

        if (onStartSquare(board.getPiece(square), square)) {
            newSquare = getNextSquare(color, newSquare);
            if (!Square.isValid(newSquare)) {
                return moves;
            }
            if (board.getPiece(newSquare) != 0) {
                return moves;
            }
            moves.add(new Move(square, newSquare));
        }

        return moves;
    }

    private static byte getNextSquare(Color color, byte currentSquare)
    {
        byte orientation = (color == Color.WHITE ? whitePawnOrientation[0] : blackPawnOrientation[0]);
        return (byte) (currentSquare + orientation);
    }

    private static List<Move> getCaptures(Board board, Color color, byte square)
    {
        List<Move> moves = new ArrayList<>();

        byte newSquare = (byte) (getNextSquare(color, square) + 1);
        if (!Square.isValid(newSquare)) {
            return moves;
        }
        byte possiblePiece = board.getPiece(newSquare);
        Color possiblePieceColor = (possiblePiece < 0 ? Color.BLACK : Color.WHITE);
        if (possiblePiece != 0 && color != possiblePieceColor) {
            moves.add(new Move(square, newSquare));
        }

        newSquare = (byte) (getNextSquare(color, square) - 1);
        if (!Square.isValid(newSquare)) {
            return moves;
        }
        possiblePiece = board.getPiece(newSquare);
        possiblePieceColor = (possiblePiece < 0 ? Color.BLACK : Color.WHITE);
        if (possiblePiece != 0 && color != possiblePieceColor) {
            moves.add(new Move(square, newSquare));
        }

        return moves;
    }

    private static boolean onStartSquare(byte piece, byte square)
    {
        if (piece == Piece.WHITE_PAWN && Square.getRank(square) == 2) {
            return true;
        }
        if (piece == Piece.BLACK_PAWN && Square.getRank(square) == 7) {
            return true;
        }
        return false;
    }

}
