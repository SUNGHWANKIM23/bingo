package com.nhnacademy.Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    
    public static void main(String[] args) {
        //게임 준비
        int port = 1234;
        int playerCount = 0;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버에 연결되었습니다.");

            //플레이어가 2명이 될 때까지 받기
            while(playerCount < 3){
                Socket socket = serverSocket.accept();
                Player player = new Player(socket, playerCount);
                player.start();
                playerCount++;
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        //게임 start
        System.out.println("게임을 시작합니다.");

        //TODO 빙고게임 진행 코드 추가
    }
}
