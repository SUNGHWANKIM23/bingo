
package main.java.com.nhnacademy.Bingo;

import com.nhnacademy.Bingo.BingoBoard;

package com.nhnacademy.Bingo;


public class Test {
    public static void main(String[] args) {
        BingoBoard board = new BingoBoard(5);
        board.bingoShuffle();
        board.printBingo();

        board.pickNumber();
    }
}

        board.winPlayer();
    }
}
