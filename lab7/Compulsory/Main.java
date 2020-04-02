package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    private static int tokenMaxValue = 50;
    private static int tokensNo = 50;
    private static int playersNo = 2;
    private static int maxArithmeticNo = 5;

    public static void main(String[] args) {
        //Generate n(noted as maxTokens) random tokens
        ArrayList<Token> tokens = new ArrayList<Token>();

        for (int i = 1; i <= tokensNo; i++){
            Token token = null;

            //Add a 10% chance to generate an empty token
            double chance = Math.random();
            if (chance < 0.1)
                token = new Token();
            else {
                //Get a random number for that token between 1 and m (noted as tokenMaxValue)
                int number = new Random().nextInt(tokenMaxValue) + 1;
                token = new Token(number);
            }
            tokens.add(token);
        }
        System.out.println(tokens);

        //Create the board, the players and the game
        Board board = new Board(tokens);
        Player players[] = new Player[playersNo];
        players[0] = new Player("Stefan", board);
        players[1] = new Player("Oana", board);

        Game game = new Game(players, board, maxArithmeticNo);
        game.play();
    }
}
