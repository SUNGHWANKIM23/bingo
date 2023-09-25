package com.nhnacademy.Bingo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class BingoBoard {
    Socket socket;
    
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

    //직접 입력해서 숫자 넣기
    public static void selectBingoNum(Socket socket) throws IOException{
        
        int maxNum = size*size;
        Scanner scanner = new Scanner(new InputStreamReader(socket.getInputStream()));
        boolean isDuplicate = false;
        //socket.getInputStrean or System.in
        for(int i = 0; i < size ; i++){
            for(int j = 0; j < size; j++){
                
                while(!isDuplicate){
                    System.out.println("빙고판 안에 넣을 숫자를 입력하세요.");  
                    int selectNum = scanner.nextInt();
                    
                    //중복된 숫자인지 확인
                    for(int k = 0; k < i; k++){
                        for(int l = 0; l < j; l++){
                            if(bingo[k][j] == selectNum){
                                isDuplicate = true;
                                System.out.println("중복된 숫자입니다. 다른 숫자를 입력하세요.");
                                break;
                            }
                        }
                    }
                    if(!isDuplicate){
                        bingo[i][j] = selectNum;
                        break;
                    }
                }
            }
        }
        System.out.println("빙고판 안의 숫자가 모두 채워졌습니다.");
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
