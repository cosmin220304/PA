package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    ArrayList<Token> tokens = new ArrayList<Token>();
    boolean available = true;

    public Board(ArrayList<Token> tokens) {
        System.out.println(tokens);
        this.tokens = tokens;
    }

    //A lock should be required before calling the getToken function
    //Get function simply get 1 random token from list
    public synchronized Token getToken() {
        if (available) {
            System.out.println("Forgot to lock getToken!!!");
            return null;
        }

        if (isTokensEmpty())
            return null;

        int randomPos = new Random().nextInt(tokens.size());
        Token token = tokens.get(randomPos);
        tokens.remove(token);
        return token;
    }

    public synchronized void lock() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        this.available = false;
        notifyAll();
    }

    public synchronized void unlock() {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        this.available = true;
        notifyAll();
    }

    public boolean isTokensEmpty(){
        return tokens.isEmpty();
    }
}
