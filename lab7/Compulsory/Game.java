package com.company;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Game {
    Player players[];
    Board board;
    int maxArithmeticNo;
    boolean turnPassed;

    public Game(Player[] players, Board board, int maxArithmeticNo) {
        this.players = players;
        this.board = board;
        this.maxArithmeticNo = maxArithmeticNo;
    }

    public void play() {
        System.out.println("start");
        for (Player player: players){
            new Thread(player).start();
        }

        while (!board.isTokensEmpty()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Player player: players){
           player.stopGame();
        }

        for (Player player: players){
            System.out.println(player);
        }

        System.out.println("end");
    }
}