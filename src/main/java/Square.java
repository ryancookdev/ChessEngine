package software.ryancook;

public class Square
{
    public static final byte A1 = 0;
    public static final byte B1 = 1;
    public static final byte C1 = 2;
    public static final byte D1 = 3;
    public static final byte E1 = 4;
    public static final byte F1 = 5;
    public static final byte G1 = 6;
    public static final byte H1 = 7;
    public static final byte A2 = 16;
    public static final byte B2 = 17;
    public static final byte C2 = 18;
    public static final byte D2 = 19;
    public static final byte E2 = 20;
    public static final byte F2 = 21;
    public static final byte G2 = 22;
    public static final byte H2 = 23;
    public static final byte A3 = 32;
    public static final byte B3 = 33;
    public static final byte C3 = 34;
    public static final byte D3 = 35;
    public static final byte E3 = 36;
    public static final byte F3 = 37;
    public static final byte G3 = 38;
    public static final byte H3 = 39;
    public static final byte A4 = 48;
    public static final byte B4 = 49;
    public static final byte C4 = 50;
    public static final byte D4 = 51;
    public static final byte E4 = 52;
    public static final byte F4 = 53;
    public static final byte G4 = 54;
    public static final byte H4 = 55;
    public static final byte A5 = 64;
    public static final byte B5 = 65;
    public static final byte C5 = 66;
    public static final byte D5 = 67;
    public static final byte E5 = 68;
    public static final byte F5 = 69;
    public static final byte G5 = 70;
    public static final byte H5 = 71;
    public static final byte A6 = 80;
    public static final byte B6 = 81;
    public static final byte C6 = 82;
    public static final byte D6 = 83;
    public static final byte E6 = 84;
    public static final byte F6 = 85;
    public static final byte G6 = 86;
    public static final byte H6 = 87;
    public static final byte A7 = 96;
    public static final byte B7 = 97;
    public static final byte C7 = 98;
    public static final byte D7 = 99;
    public static final byte E7 = 100;
    public static final byte F7 = 101;
    public static final byte G7 = 102;
    public static final byte H7 = 103;
    public static final byte A8 = 112;
    public static final byte B8 = 113;
    public static final byte C8 = 114;
    public static final byte D8 = 115;
    public static final byte E8 = 116;
    public static final byte F8 = 117;
    public static final byte G8 = 118;
    public static final byte H8 = 119;

    public static boolean isValid(byte square)
    {
        return (square & 0x88) == 0;
    }

    public static int getRank(byte square)
    {
        if (square >= 0 && square <= 7) {
            return 1;
        } if (square >= 16 && square <= 23) {
            return 2;
        } if (square >= 32 && square <= 39) {
            return 3;
        } if (square >= 48 && square <= 55) {
            return 4;
        } if (square >= 64 && square <= 71) {
            return 5;
        } if (square >= 80 && square <= 87) {
            return 6;
        } if (square >= 96 && square <= 103) {
            return 7;
        } if (square >= 112 && square <= 119) {
            return 8;
        }
        return 0;
    }
}
