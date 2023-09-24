package main.java.com.nhnacademy.Bingo;

public class BingoSystem {
    final int SIZE = 5;
    int[][] bingo = new int[SIZE][SIZE];
    private String onePlayer = "O";
    private String twoPlayer = "X";
    public String line;

    public String rowBingoCheck() {
        for (int i = 0; i < SIZE; i++) {
            int oneCount = 0;
            int twoCount = 0;
            for (int j = 0; j < SIZE; j++) {
                if (bingo[i][j] == 0) {
                    oneCount++;
                    if (oneCount == 5) {
                        return onePlayer;
                    }
                } else if (bingo[i][j] == 1) {
                    twoCount++;
                    if (twoCount == 5) {
                        return twoPlayer;
                    }
                }
            }
        }
    }

    public static rCheck() {
        for(int i=0; i<5; i++) {
            int zeroCount = 0;
            for(int j=0;j<5;j++) {
                if(map[i][j]==0) zeroCount++;
            }
            if(zeroCount==5) count++;
        }
    }

    public static void cCheck() {
        for (int i = 0; i < 5; i++) {
            int zeroCount = 0;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 0)
                    zeroCount++;
            }
            if (zeroCount == 5)
                count++;
        }
    }

    public static void leftCheck() {
        int zeroCount = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] == 0)
                zeroCount++;
        }
        if (zeroCount == 5)
            count++;
    }

    public static void rightCheck() {
        int zeroCount = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][4 - i] == 0)
                zeroCount++;
        }
        if (zeroCount == 5)
            count++;
    }
}
