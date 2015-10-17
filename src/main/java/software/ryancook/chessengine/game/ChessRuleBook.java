package software.ryancook.chessengine.game;

import software.ryancook.gameengine.Move;
import software.ryancook.generics.MultiLevelQueue;
import java.util.*;

public class ChessRuleBook
{
    public List<Move> getMoves(ChessGameState gameState)
    {
        List<Move> moves = new ArrayList<>();
        HashMap<ChessSquare, ChessPiece> activePieces = gameState.getActivePieces();
        Set<ChessSquare> pieces = activePieces.keySet();
        for (ChessSquare square: pieces) {
            ChessPiece piece = activePieces.get(square);
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

    private List<Move> getLegalKingMoves(ChessGameState gameState, ChessSquare square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<ChessSquare> squares = square.getKingMoves();
        while (squares.size() > 0) {
            ChessSquare newSquare = squares.next();
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new ChessMove(square, newSquare));
        }
        return moves;
    }

    private List<Move> getLegalQueenMoves(ChessGameState gameState, ChessSquare square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<ChessSquare> squares = square.getQueenMoves();
        while (squares.size() > 0) {
            ChessSquare newSquare = squares.next();
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

    private List<Move> getLegalRookMoves(ChessGameState gameState, ChessSquare square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<ChessSquare> squares = square.getRookMoves();
        while (squares.size() > 0) {
            ChessSquare newSquare = squares.next();
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

    private List<Move> getLegalBishopMoves(ChessGameState gameState, ChessSquare square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<ChessSquare> squares = square.getBishopMoves();
        while (squares.size() > 0) {
            ChessSquare newSquare = squares.next();
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

    private List<Move> getLegalKnightMoves(ChessGameState gameState, ChessSquare square)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<ChessSquare> squares = square.getKnightMoves();
        while (squares.size() > 0) {
            ChessSquare newSquare = squares.next();
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new ChessMove(square, newSquare));
        }
        return moves;
    }

    private List<Move> getLegalPawnMoves(ChessGameState gameState, ChessSquare square, Color color)
    {
        List<Move> moves = new ArrayList<>();
        MultiLevelQueue<ChessSquare> squares = square.getPawnMoves(color);
        boolean canAdvance = true;
        while (squares.size() > 0) {
            ChessSquare newSquare = squares.next();
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

    private boolean validPawnCapture(ChessGameState gameState, ChessSquare startSquare, ChessSquare captureSquare)
    {
        ChessPiece possiblePiece = gameState.getPiece(captureSquare);
        if (possiblePiece == ChessPiece.NULL) {
            return false;
        }

        ChessPiece piece = gameState.getPiece(startSquare);
        if (piece.getColor() == possiblePiece.getColor()) {
            return false;
        }

        return true;
    }

    private boolean squareIsEmpty(ChessGameState gameState, ChessSquare square)
    {
        return gameState.getPiece(square) == ChessPiece.NULL;
    }

    private boolean sameColorOnBothSquares(ChessGameState gameState, ChessSquare square1, ChessSquare square2)
    {
        if (squareIsEmpty(gameState, square1) || squareIsEmpty(gameState, square2)) {
            return false;
        }
        Color color1 = gameState.getPiece(square1).getColor();
        Color color2 = gameState.getPiece(square2).getColor();
        return (color1 == color2);
    }

    public boolean isKingInCheck(ChessGameState gameState)
    {
        ChessGameState newGameState = new ChessGameState(gameState);
        playNullMove(newGameState);
        List<Move> moves = getMoves(newGameState);
        for (Move m : moves) {
            ChessMove move = (ChessMove) m;
            if (newGameState.getPiece(move.getEndSquare()).isKing()) {
                return true;
            }
        }
        return false;
    }

    private void playNullMove(ChessGameState gameState)
    {
        Color otherColor = (gameState.getColorToMove() == Color.WHITE ? Color.BLACK : Color.WHITE);
        gameState.setActivePieces(otherColor);
    }
}
