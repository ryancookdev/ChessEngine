package software.ryancook;

public class Board
{
    private Piece[] board;
    private Movement[] blackPieces;
    private Movement[] whitePieces;

    public Board()
    {
        board = new Piece[128];
        blackPieces = new Movement[16];
        whitePieces = new Movement[16];
    }

    public Piece getPiece(byte square)
    {
        return board[square];
    }

    public void initialPosition()
    {
        setPiece(Square.A1, Movement.WHITE_ROOK);
        setPiece(Square.B1, Movement.WHITE_KNIGHT);
        setPiece(Square.C1, Movement.WHITE_BISHOP);
        setPiece(Square.D1, Movement.WHITE_QUEEN);
        setPiece(Square.E1, Movement.WHITE_KING);
        setPiece(Square.F1, Movement.WHITE_BISHOP);
        setPiece(Square.G1, Movement.WHITE_KNIGHT);
        setPiece(Square.H1, Movement.WHITE_ROOK);
        setPiece(Square.A2, Movement.WHITE_PAWN);
        setPiece(Square.B2, Movement.WHITE_PAWN);
        setPiece(Square.C2, Movement.WHITE_PAWN);
        setPiece(Square.D2, Movement.WHITE_PAWN);
        setPiece(Square.E2, Movement.WHITE_PAWN);
        setPiece(Square.F2, Movement.WHITE_PAWN);
        setPiece(Square.G2, Movement.WHITE_PAWN);
        setPiece(Square.H2, Movement.WHITE_PAWN);
        setPiece(Square.A7, Movement.BLACK_PAWN);
        setPiece(Square.B7, Movement.BLACK_PAWN);
        setPiece(Square.C7, Movement.BLACK_PAWN);
        setPiece(Square.D7, Movement.BLACK_PAWN);
        setPiece(Square.E7, Movement.BLACK_PAWN);
        setPiece(Square.F7, Movement.BLACK_PAWN);
        setPiece(Square.G7, Movement.BLACK_PAWN);
        setPiece(Square.H7, Movement.BLACK_PAWN);
        setPiece(Square.A8, Movement.BLACK_ROOK);
        setPiece(Square.B8, Movement.BLACK_KNIGHT);
        setPiece(Square.C8, Movement.BLACK_BISHOP);
        setPiece(Square.D8, Movement.BLACK_QUEEN);
        setPiece(Square.E8, Movement.BLACK_KING);
        setPiece(Square.F8, Movement.BLACK_BISHOP);
        setPiece(Square.G8, Movement.BLACK_KNIGHT);
        setPiece(Square.H8, Movement.BLACK_ROOK);
    }

    public void movePiece(Move move)
    {
        board[move.endSquare] = board[move.startSquare];
        board[move.startSquare] = null;
        board[move.endSquare].square = move.endSquare;
    }

    private void setPiece(byte square, byte pieceType)
    {
        Piece piece = new Bishop(pieceType, this, square);
        setPieceOnBoard(square, piece);
        updatePieceRecord(piece);
    }

    private void updatePieceRecord(Movement piece)
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
