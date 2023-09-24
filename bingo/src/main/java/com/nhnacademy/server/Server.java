package com.nhnacademy.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.nhnacademy.bingo.BingoBoard;

public class Server {
    Socket socket;
    static BufferedReader socketIn;
    static BufferedWriter socketOut;

    public static void gameStart() throws IOException {
        for (Player player : Player.playerList) {
            // player한테 묻기
            // System.out.println("몇번을 선택하시겠습니까?"); - 서버에 띄우기
            socketOut.write("몇번을 선택하시겠습니까? \n");
            socketOut.flush();
            
            Scanner sc = new Scanner(socketIn);
            String line = sc.nextLine();

            try {
                // 해당 player가 입력했다면
                // 그 값을 서버로 보냄
                socketOut.write(line + "\n");
                socketOut.flush();

                BingoBoard.pickNumber();    // 여기 안에 이겼을 경우를 이미 넣었는데..?
                BingoBoard.printBingo();
            } catch (IOException e) {
                System.out.println(e);
                // e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        // 게임 준비
        int port = 1234;
        int playerCount = 0;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버에 연결되었습니다.");

            // 플레이어가 2명이 될 때까지 받기
            while (playerCount < 3) {
                Socket socket = serverSocket.accept();
                Player player = new Player(socket, playerCount);
                player.start();
                playerCount++;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        // 게임 start
        System.out.println("게임을 시작합니다.");

        // TODO 빙고게임 진행 코드 추가
        try {
            gameStart();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
