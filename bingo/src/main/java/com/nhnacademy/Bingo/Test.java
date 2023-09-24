package com.nhnacademy.Bingo;

public class Test {
    public static void main(String[] args) {
        BingoBoard board = new BingoBoard(5);
        board.bingoShuffle();
        board.printBingo();
        board.winPlayer();
    }
}
