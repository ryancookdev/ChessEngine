package software.ryancook.chess.engine;

import software.ryancook.chess.util.*;
import software.ryancook.gameengine.Move;
import software.ryancook.complexcollections.MultiLevelQueue;
import java.util.*;

class RuleBook
{
    public List<Move> getMoves(final ChessGameState gameState)
    {
        List<Move> moves = getPotentialMoves(gameState);
        moves = removeIfKingInCheck(gameState, moves);
        return moves;
    }

    private List<Move> getPotentialMoves(final ChessGameState gameState)
    {
        final List<Move> moves = new ArrayList<>();
        final HashMap<Square, Piece> activePieces = gameState.getActivePieces();
        final Set<Square> pieces = activePieces.keySet();
        for (final Square square: pieces) {
            final Piece piece = activePieces.get(square);
            if (piece.isKing()) {
                moves.addAll(getLegalKingMoves(gameState, square));
            } else if (piece.isQueen()) {
                moves.addAll(getLegalQueenMoves(gameState, square));
            } else if (piece.isRook()) {
                moves.addAll(getLegalRookMoves(gameState, square));
            } else if (piece.isBishop()) {
                moves.addAll(getLegalBishopMoves(gameState, square));
            } else if (piece.isKnight()) {
                moves.addAll(getLegalKnightMoves(gameState, square));
            } else if (piece.isPawn() && piece.isWhite()) {
                moves.addAll(getLegalPawnMoves(gameState, square, Color.WHITE));
            } else if (piece.isPawn() && piece.isBlack()) {
                moves.addAll(getLegalPawnMoves(gameState, square, Color.BLACK));
            }
        }
        return moves;
    }

    private List<Move> removeIfKingInCheck(final ChessGameState gameState, final List<Move> moves)
    {
        final List<Move> filteredMoves = new ArrayList<>();
        for (final Move move : moves) {
            if (!movePutsKingInCheck(gameState, move)) {
                filteredMoves.add(move);
            }
        }
        return filteredMoves;
    }

    private boolean movePutsKingInCheck(final ChessGameState gameState, final Move move)
    {
        ChessGameState newGameState = (ChessGameState) gameState.playMove(move);
        newGameState = playNullMove(newGameState);
        return isCheck(newGameState);
    }

    private List<Move> getLegalKingMoves(final ChessGameState gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getKingMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new ChessMove(square, newSquare));
        }
        return moves;
    }

    private List<Move> getLegalQueenMoves(final ChessGameState gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getQueenMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new ChessMove(square, newSquare));
        }
        return moves;
    }

    private List<Move> getLegalRookMoves(final ChessGameState gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getRookMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new ChessMove(square, newSquare));
        }
        return moves;
    }

    private List<Move> getLegalBishopMoves(final ChessGameState gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getBishopMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new ChessMove(square, newSquare));
        }
        return moves;
    }

    private List<Move> getLegalKnightMoves(final ChessGameState gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getKnightMoves();
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new ChessMove(square, newSquare));
        }
        return moves;
    }

    private List<Move> getLegalPawnMoves(final ChessGameState gameState, final Square square, Color color)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getPawnMoves(color);
        boolean canAdvance = true;
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (square.sameDiagonal(newSquare)) {
                if (validPawnCapture(gameState, square, newSquare)) {
                    moves.add(new ChessMove(square, newSquare));
                }
            } else {
                if (canAdvance && squareIsEmpty(gameState, newSquare)) {
                    moves.add(new ChessMove(square, newSquare));
                } else {
                    canAdvance = false;
                }
            }
        }
        return moves;
    }

    private boolean validPawnCapture(final ChessGameState gameState, final Square startSquare, final Square captureSquare)
    {
        final Piece possiblePiece = gameState.getPiece(captureSquare);
        if (possiblePiece == Piece.NULL) {
            return false;
        }

        final Piece piece = gameState.getPiece(startSquare);
        if (piece.getColor() == possiblePiece.getColor()) {
            return false;
        }

        return true;
    }

    private boolean squareIsEmpty(final ChessGameState gameState, final Square square)
    {
        return gameState.getPiece(square) == Piece.NULL;
    }

    private boolean sameColorOnBothSquares(final ChessGameState gameState, final Square square1, final Square square2)
    {
        if (squareIsEmpty(gameState, square1) || squareIsEmpty(gameState, square2)) {
            return false;
        }
        final Color color1 = gameState.getPiece(square1).getColor();
        final Color color2 = gameState.getPiece(square2).getColor();
        return (color1 == color2);
    }

    public boolean isCheck(final ChessGameState gameState)
    {
        final ChessGameState newGameState = playNullMove(gameState);
        final List<Move> moves = getPotentialMoves(newGameState);
        for (final Move m : moves) {
            final ChessMove move = (ChessMove) m;
            if (newGameState.getPiece(move.getEndSquare()).isKing()) {
                return true;
            }
        }
        return false;
    }

    public boolean isMate(final ChessGameState gameState)
    {
        if (!isCheck(gameState)) {
            return false;
        }
        return allMovesPutKingInCheck(gameState);
    }

    public boolean isStalemate(final ChessGameState gameState)
    {
        if (isCheck(gameState)) {
            return false;
        }
        return allMovesPutKingInCheck(gameState);
    }

    private boolean allMovesPutKingInCheck(final ChessGameState gameState)
    {
        final List<Move> moves = getPotentialMoves(gameState);
        for (final Move m : moves) {
            ChessMove move = (ChessMove) m;
            if (!movePutsKingInCheck(gameState, move)) {
                return false;
            }
        }
        return true;
    }

    private ChessGameState playNullMove(final ChessGameState gameState)
    {
        return gameState.getBuilder().togglePlayerToMove().build();
    }
}
