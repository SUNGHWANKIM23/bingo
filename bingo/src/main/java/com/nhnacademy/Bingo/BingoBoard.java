package com.nhnacademy.Bingo;
public class BingoBoard {
    //빙고판 초기화
    static int[][] bingo;
    static int size;
    //n*n크기의 빙고판 생성
    public BingoBoard(int size){
        this.size = size;
        bingo = new int[size][size];
        for(int i = 0; i< size; i++){
            for(int j = 0; j < size; j++){
                bingo[i][j] = i*size +j + 1; 
            } 
        }
    }

    //랜덤으로 숫자 넣기
    public static void bingoShuffle(){
        for(int i = 0; i< size; i++){
            for(int j = 0; j < size; j++){
                int x = (int)(Math.random() * size);
                int y = (int)(Math.random() * size);

                //빙고 숫자 변경
                int tmp = bingo[i][j];
                bingo[i][j] = bingo[x][y];
                bingo[x][y] = tmp;
            }
        }
    }

    //빙고판 출력
    public static void printBingo(){
        System.out.println("빙고판을 출력합니다.");
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(bingo[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BingoBoard bingo = new BingoBoard(3);
        bingo.bingoShuffle();
        bingo.printBingo();
    }
}
