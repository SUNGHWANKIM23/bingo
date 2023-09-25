package com.nhnacademy.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.nhnacademy.bingo.BingoBoard;

public class Server {
    static boolean ready = false;

    Socket socket;
    static BufferedReader socketIn;
    static BufferedWriter socketOut;


    public static void main(String[] args) {
        // 게임 준비
        int port = 1234;
        int playerCount = 1;
        int count = 0;
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
        ready = true;

        // 첫번째 플레이어부터 시작 (먼저 들어온 사람이 첫번째)
        int turnOwner = 1;

        // player가 끝나는거 기다렸다가 종료
        try {
            Player.playerList.get(0).join();
            Player.playerList.get(1).join();
        } catch (InterruptedException e) {
            System.out.println(e.toString());

        }
        while (ready) {
            try {
                if(turnOwner == 1) {
                    Player.playerList.get(0).gameStart();
                    turnOwner = 2;
                } else {
                    Player.playerList.get(1).gameStart();
                    turnOwner = 1;
                }
                count++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(count > 25){
                ready =false;
            }
        }


        System.out.println("end");
        // TODO 빙고게임 진행 코드 추가
        
    }
}
