package com.nhnacademy.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Player extends Thread {
    static List<Player> playerList = new LinkedList<>();
    Socket socket;
    int num;
    String piece;
    static BufferedReader sysIn;
    BufferedWriter sysOut;

    BufferedReader socketIn;
    BufferedWriter socketOut;

    public Player(Socket socket, int num) {
        this.socket = socket;
        this.num = num;
        pickPiece();
    }

    public void buffer() {
        sysIn = new BufferedReader(new InputStreamReader(System.in));
        sysOut = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 플레이어 문양 선택
    public void pickPiece() {
        if (num == 1) {
            piece = "O";
        } else {
            piece = "X";
        }
    }
    
    public void choose() {
        try{
           while() {
            
           } 
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void run() {
        System.out.println("test" + piece);
        try {
            Server.gameStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
