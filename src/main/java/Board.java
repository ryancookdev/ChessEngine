public class Board
{
    private Piece[] board;
    private Piece[] blackPieces;
    private Piece[] whitePieces;

    public Board()
    {
        board = new Piece[128];
        blackPieces = new Piece[16];
        whitePieces = new Piece[16];
    }

    public Piece getPiece(byte square)
    {
        return board[square];
    }

    public void initialPosition()
    {
        setPiece(Square.A1, Piece.WHITE_ROOK);
        setPiece(Square.B1, Piece.WHITE_KNIGHT);
        setPiece(Square.C1, Piece.WHITE_BISHOP);
        setPiece(Square.D1, Piece.WHITE_QUEEN);
        setPiece(Square.E1, Piece.WHITE_KING);
        setPiece(Square.F1, Piece.WHITE_BISHOP);
        setPiece(Square.G1, Piece.WHITE_KNIGHT);
        setPiece(Square.H1, Piece.WHITE_ROOK);
        setPiece(Square.A2, Piece.WHITE_PAWN);
        setPiece(Square.B2, Piece.WHITE_PAWN);
        setPiece(Square.C2, Piece.WHITE_PAWN);
        setPiece(Square.D2, Piece.WHITE_PAWN);
        setPiece(Square.E2, Piece.WHITE_PAWN);
        setPiece(Square.F2, Piece.WHITE_PAWN);
        setPiece(Square.G2, Piece.WHITE_PAWN);
        setPiece(Square.H2, Piece.WHITE_PAWN);
        setPiece(Square.A7, Piece.BLACK_PAWN);
        setPiece(Square.B7, Piece.BLACK_PAWN);
        setPiece(Square.C7, Piece.BLACK_PAWN);
        setPiece(Square.D7, Piece.BLACK_PAWN);
        setPiece(Square.E7, Piece.BLACK_PAWN);
        setPiece(Square.F7, Piece.BLACK_PAWN);
        setPiece(Square.G7, Piece.BLACK_PAWN);
        setPiece(Square.H7, Piece.BLACK_PAWN);
        setPiece(Square.A8, Piece.BLACK_ROOK);
        setPiece(Square.B8, Piece.BLACK_KNIGHT);
        setPiece(Square.C8, Piece.BLACK_BISHOP);
        setPiece(Square.D8, Piece.BLACK_QUEEN);
        setPiece(Square.E8, Piece.BLACK_KING);
        setPiece(Square.F8, Piece.BLACK_BISHOP);
        setPiece(Square.G8, Piece.BLACK_KNIGHT);
        setPiece(Square.H8, Piece.BLACK_ROOK);
    }

    public void movePiece(Move move)
    {
        board[move.endSquare] = board[move.startSquare];
        board[move.startSquare] = null;
        board[move.endSquare].square = move.endSquare;
    }

    private void setPiece(byte square, byte pieceType)
    {
        Piece piece = new Piece(pieceType, this, square);
        setPieceOnBoard(square, piece);
        updatePieceRecord(piece);
    }

    private void updatePieceRecord(Piece piece)
    {
        for (int i = 0; i < 16; i++) {
            if (whitePieces[i] == null) {
                whitePieces[i] = piece;
                return;
            }
        }
    }

    private void setPieceOnBoard(byte square, Piece piece)
    {
        board[square] = piece;
    }
}
