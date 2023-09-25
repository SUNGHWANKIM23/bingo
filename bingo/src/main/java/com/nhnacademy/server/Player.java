package com.nhnacademy.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.nhnacademy.bingo.BingoBoard;

public class Player extends Thread {
    static List<Player> playerList = new LinkedList<>();
    BufferedReader sysIn; // 서버에 출력
    BufferedWriter sysOut;
    BufferedReader socketIn; // 소켓에 출력
    BufferedWriter socketOut;

    BingoBoard bingoBoard;
    Socket socket;
    int num;
    String piece;
    // 빙고 사이즈
    int size = 5;

    static String beforePlayer="Thread-1";

    public Player(Socket socket, int num) {
        this.socket = socket;
        this.num = num;
        pickPiece();
        buffer();
        playerList.add(this);
    }

    // 플레이어 문양 선택
    public void pickPiece() {
        if (num == 1) {
            piece = "=";
        } else {
            piece = "X";
        }
    }

    // buffered Reader && Writer
    public void buffer() {
        sysIn = new BufferedReader(new InputStreamReader(System.in));
        sysOut = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        sendAll("플레이어" + num + " 입장");

        // 게임이 시작되기를 기다림
        while (true) {
            if (Server.ready == true) {
                break;
            }
            yield();
        }
        // 게임 
        sendMe("player : " + num + ", piece : " + piece);

        // 빙고판 만들기
        bingoBoard = new BingoBoard(5);
        // 빙고판 초기화
        bingoBoard.bingoShuffle();
        // 출력
        bingoBoard.printBingo(socketOut, piece);
        bingoBoard.printBingo(sysOut, piece);
        
        
    }

    public void gameStart() throws IOException {
        // socketOut - player 출력
        // sysOut - 서버 출력
        try {
            // player한테 묻기
            socketOut.write("몇 번을 선택하겠습니까?");
            socketOut.newLine();
            socketOut.flush();
            System.out.println("플레이어" + num);
            // scanner 받을 때까지 기다리기
            Scanner scanner = new Scanner(socketIn);
            String pickNum = scanner.nextLine();
            while(pickNum != null) {

                
                /* if(scanner == null) {
                    Thread.currentThread().wait();
                } else{
                    Thread.currentThread().notifyAll();
                } */
                //-----------------------------------
                // 해당 player가 입력했다면
                // 그 값을 서버로 보냄
                bingoBoard.pickNumber(pickNum);
                bingoBoard.printBingo(socketOut, piece);    // 
                
                // 서버에 띄우기
                /* sysOut.write(Thread.currentThread().getName() +  num + pickNum); // 여기 안나옴
                sysOut.newLine();
                sysOut.flush(); */
                bingoBoard.printBingo(sysOut, piece);    // 여기 ㅇㅋ
                System.out.println("입력완료 - " + "플레이어" + num);
                break;
            }
            } catch (Exception e) {
            System.out.println(e);
            // e.printStackTrace();
        }

    }

    public void sendMe(String message) {
        try {
            socketOut.write(message + "\n");
            socketOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAll(String message) {
        try {
            for (Player player : playerList) {
                player.socketOut.write(message + "\n");
                player.socketOut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println("error");
        }
    }

    public void sendServer(String message) {
        try {
            sysOut.write(message + "\n");
            sysOut.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
