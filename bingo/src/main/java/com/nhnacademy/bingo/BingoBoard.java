package com.nhnacademy.bingo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import com.nhnacademy.server.Player;

public class BingoBoard extends Thread {
    // 빙고판 초기화
    int[][] bingo;
    static int size;
    private static String onePlayerId;
    private static String twoPlayerId; // 여기가 비어 있어서 null이 나오는거임. 플레이어 이름 추가하라고 일부러 만들어놓은 2줄.
    static int onePlayerCount = 0;
    static int twoPlayerCount = 0;

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
    public void bingoShuffle() {
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
    public void printBingo(BufferedWriter writer, String piece) {
        try {
            writer.write("빙고판을 출력합니다.\n");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(bingo[i][j] == 0) {
                        writer.write(piece+" ");
                    }
                    else{
                        writer.write(bingo[i][j] + " ");
                    }
                }
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //
    public void pickNumber(String picknum) {
        for (int a = 1; a <= (size * size); a++) {

            // System.in을 inputStreamReader(Socket.in);으로
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (bingo[i][j] == Integer.valueOf(picknum))
                        bingo[i][j] = 0; // Player1 = "O" , Player2 = "X"
                }
            }

            winCheck();

            if ((onePlayerCount >= 1) && (twoPlayerCount >= 1)) {
                System.out.println("draw");
            } else if (onePlayerCount >= 1) {// 여기 숫자 바꾸면 빙고 1줄부터 12줄까지 가능하니까 입맛대로 고르면 됨.
                System.out.println(onePlayerId + " is winner");
            } else if (twoPlayerCount >= 1) // 여기도 이하동일
                System.out.println(twoPlayerId + " is winner");
        }
    }

    public void winCheck() {
        rowBingoCheck();
        columnBingoCheck();
        leftDiagonal();
        rightDiagonal();

    }

    public void rowBingoCheck() { // 행
        for (int i = 0; i < size; i++) {
            int oneCount = 0;
            int twoCount = 0;
            for (int j = 0; j < size; j++) {
                if (bingo[i][j] == 0) {
                    oneCount++;
                    if (oneCount == size) { // 한 줄에 사이즈가 5개니까.카운트를 사이즈로 바꾸면 되지
                        onePlayerCount++;
                    }
                }
                if (bingo[i][j] == 1) {
                    twoCount++;
                    if (twoCount == size) {
                        twoPlayerCount++;
                    }
                }
            }
        }
    }

    public void columnBingoCheck() { // 열
        for (int i = 0; i < size; i++) {
            int oneCount = 0;
            int twoCount = 0;
            for (int j = 0; j < size; j++) {
                if (bingo[j][i] == 0) {
                    oneCount++;
                    if (oneCount == size) {
                        onePlayerCount++;
                    }
                }
                if (bingo[j][i] == 1) {
                    twoCount++;
                    if (twoCount == size) {
                        twoPlayerCount++;
                    }
                }
            }
        }
    }

    public void leftDiagonal() { // 좌 -> 우 대각선
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < size; i++) {
            if (bingo[i][i] == 0) {
                oneCount++;
                if (oneCount == size) {
                    onePlayerCount++;
                }
            }
            if (bingo[i][i] == 1) {
                twoCount++;
                if (twoCount == size) {
                    twoPlayerCount++;
                }
            }
        }
    }

    public void rightDiagonal() { // 우 -> 좌 대각선
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < size; i++) {
            if (bingo[i][4 - i] == 0) {
                oneCount++;
                if (oneCount == size) {
                    onePlayerCount++;
                }
            }
            if (bingo[i][i] == 1) {
                twoCount++;
                if (twoCount == size) {
                    twoPlayerCount++;
                }
            }
        }
    }

}