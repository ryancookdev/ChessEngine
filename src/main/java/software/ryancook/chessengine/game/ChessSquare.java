package software.ryancook.chessengine.game;

import software.ryancook.generics.MultiLevelQueue;
import java.util.*;

public enum ChessSquare
{
    NULL(-1),
    A1(0), B1(1), C1(2), D1(3), E1(4), F1(5), G1(6), H1(7),
    A2(16), B2(17), C2(18), D2(19), E2(20), F2(21), G2(22), H2(23),
    A3(32), B3(33), C3(34), D3(35), E3(36), F3(37), G3(38), H3(39),
    A4(48), B4(49), C4(50), D4(51), E4(52), F4(53), G4(54), H4(55),
    A5(64), B5(65), C5(66), D5(67), E5(68), F5(69), G5(70), H5(71),
    A6(80), B6(81), C6(82), D6(83), E6(84), F6(85), G6(86), H6(87),
    A7(96), B7(97), C7(98), D7(99), E7(100), F7(101), G7(102), H7(103),
    A8(112), B8(113), C8(114), D8(115), E8(116), F8(117), G8(118), H8(119);

    private static final Map<Integer, ChessSquare> lookup = new HashMap<>();
    private int value;

    static
    {
        for (ChessSquare square : EnumSet.allOf(ChessSquare.class)) {
            lookup.put(square.getValue(), square);
        }
    }

    public static ChessSquare getSquare(String square)
    {
        int file = square.toLowerCase().charAt(0) - 96;
        int rank = Integer.parseInt(square.substring(1, 2));
        int squareValue = (file + (16 * (rank - 1))) - 1;
        return lookup.get(squareValue);
    }

    ChessSquare(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public MultiLevelQueue<ChessSquare> getKingMoves()
    {
        return getBasicMoves(new int[]{-17, -16, -15, -1, 1, 15, 16, 17}, 1);
    }

    public MultiLevelQueue<ChessSquare> getQueenMoves()
    {
        return getBasicMoves(new int[] {-17, -16, -15, -1, 1, 15, 16, 17}, 7);
    }

    public MultiLevelQueue<ChessSquare> getRookMoves()
    {
        return getBasicMoves(new int[] {-1, -16, 1, 16}, 7);
    }

    public MultiLevelQueue<ChessSquare> getBishopMoves()
    {
        return getBasicMoves(new int[] {-17, -15, 15, 17}, 7);
    }

    public MultiLevelQueue<ChessSquare> getKnightMoves()
    {
        return getBasicMoves(new int[] {-33, -31, -18, -14, 14, 18, 31, 33}, 1);
    }

    public MultiLevelQueue<ChessSquare> getPawnMoves(Color color)
    {
        if (color == Color.WHITE) {
            return getWhitePawnMoves();
        } else {
            return getBlackPawnMoves();
        }
    }

    public MultiLevelQueue<ChessSquare> getWhitePawnMoves()
    {
        int[] directions = new int[] {15, 16, 17};
        if (getRank() == 2) {
            directions = new int[] {15, 16, 17, 32};
        }
        return getBasicMoves(directions, 1);
    }

    public MultiLevelQueue<ChessSquare> getBlackPawnMoves()
    {
        int[] directions = new int[] {-16, -15, -17};
        if (getRank() == 7) {
            directions = new int[] {-16, -15, -17, -32};
        }
        return getBasicMoves(directions, 1);
    }

    private MultiLevelQueue<ChessSquare> getBasicMoves(int[] directions, int maxDistance)
    {
        MultiLevelQueue<ChessSquare> squares = new MultiLevelQueue<>();
        for (int i = 0; i < directions.length; i++) {
            squares.addLevel("basicMoves" + i);
            for (int j = 0; j < maxDistance; j++) {
                int distance = directions[i] * (j + 1);
                int newSquareValue = value + distance;
                if (isValid(newSquareValue)){
                    squares.add(lookup.get(newSquareValue));
                }
            }
        }
        return squares;
    }

    private boolean isValid(int square)
    {
        return (square & 0x88) == 0;
    }

    public boolean sameDiagonal(ChessSquare square)
    {
        if (Math.abs(square.getValue() - value) % 15 == 0) {
            return true;
        }
        if (Math.abs(square.getValue() - value) % 17 == 0) {
            return true;
        }
        return false;
    }

    public int getRank()
    {
        return Math.floorDiv(value, 16) + 1;
    }

    @Override
    public String toString()
    {
        return getString();
    }

    public String getString()
    {
        int newValue = value + 1;
        int rank = Math.floorDiv(newValue, 16) + 1;
        int file = newValue - ((rank - 1) * 16);
        char letter = (char) (file + 96);
        return letter + Integer.toString(rank);
    }
}
