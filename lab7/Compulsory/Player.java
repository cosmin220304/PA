package com.company;

import java.util.*;

public class Player implements Runnable{
    String name;
    Board board;
    ArrayList<Token> tokens = new ArrayList<Token>();
    boolean isGameOver;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        isGameOver = false;
    }

    @Override
    public void run() {
        while(!isGameOver) {

            //Mutex Lock
            board.lock();

            //Critical Section
            Token token = board.getToken();

            //Mutex Unlock
            board.unlock();

            if (token == null)
                continue;
            tokens.add(token);
            System.out.println(name + " got " + token);
        }
    }

    public void stopGame() {
        isGameOver = true;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", tokens=" + tokens +
                '}';
    }
}
