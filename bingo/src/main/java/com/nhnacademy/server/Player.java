package com.nhnacademy.server;

import java.io.BufferedWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Player extends Thread {
    static List<Player> playerList = new LinkedList<>();
    Socket socket;
    int num;
    String piece;

    public Player(Socket socket, int num) {
        this.socket = socket;
        this.num = num;
        pickPiece();
    }

    // 플레이어 문양 선택
    public void pickPiece() {
        if (num == 1) {
            piece = "O";
        } else {
            piece = "X";
        }
    }

    @Override
    public void run() {
        System.out.println("test" + piece);
    }

}
