package com.ab.bowling;

import java.util.Scanner;

/**
 * @author Atef BECHEIKH
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Veuillez saisir Bowling input : ");
        String sequenceRoll = in.nextLine();

        BowlingGame game = new BowlingGame();
        try {
            game.setSequenceRoll(sequenceRoll);
            System.out.println("******* Table Bowling Game *******");
            game.score();
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

}