package com.nhnacademy.bingo;

import java.util.Scanner;

public class BingoBoard {
    // 빙고판 초기화
    static int[][] bingo;
    static int size;
    private static String onePlayer = "O";
    private static String twoPlayer = "X";

    static Scanner scanner = new Scanner(System.in);

    // n*n크기의 빙고판 생성
    public BingoBoard(int size) {
        this.size = size;
        bingo = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bingo[i][j] = i * size + j + 1;
            }
        }
    }

    // 랜덤으로 숫자 넣기
    public static void bingoShuffle() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int x = (int) (Math.random() * size);
                int y = (int) (Math.random() * size);

                // 빙고 숫자 변경
                int tmp = bingo[i][j];
                bingo[i][j] = bingo[x][y];
                bingo[x][y] = tmp;
            }
        }
    }

    // 빙고판 출력
    public static void printBingo() {
        System.out.println("빙고판을 출력합니다.");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(bingo[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void pickNumber() {
        for (int a = 1; a <= (size * size); a++) {
            int num = scanner.nextInt();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 5; j++) {
                    if (bingo[i][j] == num)
                        bingo[i][j] = 0;

                    System.out.print(bingo[i][j] + " ");
                }
                System.out.println();
            }

            winPlayer();
        }
    }

    public static void winPlayer() {
        rowBingoCheck();
        columnBingoCheck();
        leftDiagonal();
        rightDiagonal();
    }

    public static String rowBingoCheck() {
        for (int i = 0; i < size; i++) {
            int oneCount = 0;
            int twoCount = 0;
            for (int j = 0; j < size; j++) {
                if (bingo[i][j] == 0) {
                    oneCount++;
                    if (oneCount == 5) {
                        return onePlayer;
                    }
                }
                if (bingo[i][j] == 1) {
                    twoCount++;
                    if (twoCount == 5) {
                        return twoPlayer;
                    }
                }
            }
        }
        return null;
    }

    public static String columnBingoCheck() {
        for (int i = 0; i < size; i++) {
            int oneCount = 0;
            int twoCount = 0;
            for (int j = 0; j < size; j++) {
                if (bingo[j][i] == 0) {
                    oneCount++;
                    if (oneCount == 5) {
                        return onePlayer;
                    }
                }
                if (bingo[j][i] == 1) {
                    twoCount++;
                    if (twoCount == 5) {
                        return twoPlayer;
                    }
                }
            }
        }
        return null;

    }

    public static String leftDiagonal() {
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < 5; i++) {
            if (bingo[i][i] == 0) {
                oneCount++;
                if (oneCount == 5) {
                    return onePlayer;
                }
            }
            if (bingo[i][i] == 1) {
                twoCount++;
                if (twoCount == 5) {
                    return twoPlayer;
                }
            }
        }
        return null;
    }

    public static String rightDiagonal() {
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < 5; i++) {
            if (bingo[i][4 - i] == 0) {
                oneCount++;
                if (oneCount == 5) {
                    return onePlayer;
                }
            }
            if (bingo[i][i] == 1) {
                twoCount++;
                if (twoCount == 5) {
                    return twoPlayer;
                }
            }
        }

        return null;
    }

}
