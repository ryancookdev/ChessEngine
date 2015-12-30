package software.ryancook.chess.engine;

import software.ryancook.chess.util.Piece;
import software.ryancook.chess.util.Square;
import software.ryancook.chess.util.Color;
import software.ryancook.gameengine.*;
import java.util.*;

public class ChessGameState implements GameState
{
    private final HashMap<Square, Piece> blackPieces;
    private final HashMap<Square, Piece> whitePieces;
    private HashMap<Square, Piece> activePieces;
    private int ply;

    private ChessGameState()
    {
        blackPieces = new HashMap<>();
        whitePieces = new HashMap<>();
        activePieces = null;
        ply = 0;
    }

    public ChessGameState(final ChessGameState oldBoard)
    {
        this();

        copyPieceList(oldBoard.whitePieces);
        copyPieceList(oldBoard.blackPieces);

        if (oldBoard.getColorToMove() == Color.WHITE) {
            activePieces = whitePieces;
        } else {
            activePieces = blackPieces;
        }
        ply = oldBoard.getPly();
    }

    public Builder getBuilder()
    {
        final Builder builder = new Builder(getColorToMove());
        builder.setPly(ply);
        builder.copyPieceList(whitePieces);
        builder.copyPieceList(blackPieces);
        return builder;
    }

    public static class Builder
    {
        private final HashMap<Square, Piece> blackPieces;
        private final HashMap<Square, Piece> whitePieces;
        private HashMap<Square, Piece> activePieces;
        private int ply;

        public Builder(final Color sideToMove)
        {
            ply = 0;
            blackPieces = new HashMap<>();
            whitePieces = new HashMap<>();
            setActivePieces(sideToMove);
        }

        private void copyPieceList(final HashMap<Square, Piece> oldPieceList)
        {
            final Set<Square> pieces = oldPieceList.keySet();
            for (final Square square: pieces) {
                final Piece piece = oldPieceList.get(square);
                setPiece(piece, square);
            }
        }

        public Builder setActivePieces(final Color color)
        {
            if (color == Color.WHITE) {
                activePieces = whitePieces;
            } else {
                activePieces = blackPieces;
            }
            return this;
        }

        public Builder setPly(final int ply)
        {
            this.ply = ply;
            return this;
        }

        public Builder setPiece(final Piece piece, final Square square)
        {
            removeFromPieceList(square);
            addToPieceList(square, piece);
            return this;
        }

        private void removeFromPieceList(final Square square)
        {
            final Piece piece = getPiece(square);
            if (piece.isBlack()) {
                blackPieces.remove(square);
            } else if (piece.isWhite()) {
                whitePieces.remove(square);
            }
        }

        private void addToPieceList(final Square square, final Piece piece)
        {
            if (piece.isBlack()) {
                blackPieces.put(square, piece);
            } else if (piece.isWhite()) {
                whitePieces.put(square, piece);
            }
        }

        public Piece getPiece(final Square square)
        {
            if (whitePieces.containsKey(square)) {
                return whitePieces.get(square);
            }
            if (blackPieces.containsKey(square)) {
                return blackPieces.get(square);
            }
            return Piece.NULL;
        }

        public Color getColorToMove()
        {
            return (activePieces == whitePieces ? Color.WHITE : Color.BLACK);
        }

        public Builder togglePlayerToMove()
        {
            activePieces = (activePieces == whitePieces ? blackPieces : whitePieces);
            return this;
        }

        public ChessGameState build()
        {
            return new ChessGameState(this);
        }
    }

    private ChessGameState(Builder builder)
    {
        this();

        copyPieceList(builder.whitePieces);
        copyPieceList(builder.blackPieces);

        if (builder.getColorToMove() == Color.WHITE) {
            activePieces = whitePieces;
        } else {
            activePieces = blackPieces;
        }
        ply = builder.ply;
    }

    // Duplication
    private void copyPieceList(final HashMap<Square, Piece> oldPieceList)
    {
        final Set<Square> pieces = oldPieceList.keySet();
        for (final Square square: pieces) {
            final Piece piece = oldPieceList.get(square);
            removeFromPieceList(square);
            addToPieceList(square, piece);
        }
    }

    // Duplication
    private void removeFromPieceList(final Square square)
    {
        final Piece piece = getPiece(square);
        if (piece.isBlack()) {
            blackPieces.remove(square);
        } else if (piece.isWhite()) {
            whitePieces.remove(square);
        }
    }

    // Duplication
    private void addToPieceList(final Square square, final Piece piece)
    {
        if (piece.isBlack()) {
            blackPieces.put(square, piece);
        } else if (piece.isWhite()) {
            whitePieces.put(square, piece);
        }
    }

    public Color getColorToMove()
    {
        return (activePieces == whitePieces ? Color.WHITE : Color.BLACK);
    }

    @Override
    public int getPly()
    {
        return ply;
    }

    @Override
    public boolean isFirstPlayerToMove()
    {
        return getColorToMove() == Color.WHITE;
    }

    @Override
    public List<Move> getMoves()
    {
        final RuleBook ruleBook = new RuleBook();
        final List<Move> moves = ruleBook.getMoves(this);
        return moves;
    }

    @Override
    public Move getNullMove()
    {
        return new ChessMove();
    }

    @Override
    public GameState playMove(final Move m)
    {
        final ChessMove move = (ChessMove) m;
        final Builder builder = getBuilder();

        final Piece piece = getPiece(move.getStartSquare());
        if (piece == Piece.NULL) {
            throw new PieceNotFoundException();
        }

        builder.setPiece(piece, move.getEndSquare());
        builder.removeFromPieceList(move.getStartSquare());
        builder.togglePlayerToMove();
        builder.setPly(ply + 1);

        return builder.build();
    }

    @Override
    public List<Move> getCriticalMoves()
    {
        return new ArrayList<>();
    }

    public Piece getPiece(final Square square)
    {
        if (whitePieces.containsKey(square)) {
            return whitePieces.get(square);
        }
        if (blackPieces.containsKey(square)) {
            return blackPieces.get(square);
        }
        return Piece.NULL;
    }

    public HashMap<Square, Piece> getWhitePieces()
    {
        return whitePieces;
    }

    public HashMap<Square, Piece> getBlackPieces()
    {
        return blackPieces;
    }

    public HashMap<Square, Piece> getActivePieces()
    {
        return activePieces;
    }

    public int getTotalWhitePieces()
    {
        int total = 0;
        final Set<Square> pieces = whitePieces.keySet();
        for (final Square square: pieces) {
            if (whitePieces.get(square) != Piece.NULL) {
                total++;
            }
        }
        return total;
    }

    public int getTotalBlackPieces()
    {
        int total = 0;
        final Set<Square> pieces = blackPieces.keySet();
        for (final Square square: pieces) {
            if (blackPieces.get(square) != Piece.NULL) {
                total++;
            }
        }
        return total;
    }

    @Override
    public String toString()
    {
        final Piece[] board = new Piece[128];
        Set<Square> pieces = blackPieces.keySet();
        for (final Square square: pieces) {
            board[square.getValue()] = blackPieces.get(square);
        }
        pieces = whitePieces.keySet();
        for (final Square square: pieces) {
            board[square.getValue()] = whitePieces.get(square);
        }
        String position = " ";
        for (int i = 0; i < 64; i++) {
            final byte square = (byte) ((i + 112) - (24 * (Math.floorDiv(i, 8))));
            final Piece piece = board[square];
            position += (piece == null ? ". " : piece.getLetter() + " ");
            if ((i + 1) % 8 == 0) {
                position += "\n ";
            }
        }
        return position;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ChessGameState board = (ChessGameState) o;

        if (!blackPieces.equals(board.blackPieces)) {
            return false;
        }
        if (!whitePieces.equals(board.whitePieces)) {
            return false;
        }
        return getColorToMove() == board.getColorToMove();
    }

    @Override
    public int hashCode()
    {
        if (toString().equals(" K r . . . . . . \n . . . . . . . . \n . . . . . . . . \n . . . . . . . . \n . . . . . p . . \n . . . . . . . . \n R . . . . . . . \n . . . . . . . k \n ")) {
            int a = 0;
        }
        int result = blackPieces != null ? blackPieces.hashCode() : 0;
        result = 31 * result + (whitePieces != null ? whitePieces.hashCode() : 0);
        result = 31 * result + (activePieces != null ? activePieces.hashCode() : 0);
        return result;
    }

    class PieceNotFoundException extends RuntimeException {}
}
