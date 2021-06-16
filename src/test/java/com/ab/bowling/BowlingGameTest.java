package com.ab.bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Bowling Game Test
 *
 * @author Atef BECHEIKH
 *
 */

public class BowlingGameTest {


    private BowlingGame game;

    @Before
    public void setUp() {
        game = new BowlingGame();
    }


    /**
     * CASE 1
     * input : XXXXXXXXXXXX (12 strike) expected score equal to 300
     */

    @Test
    public void allStrikesExpected_300() {

        String sequence = "XXXXXXXXXXXX";
        game.setSequenceRoll(sequence);
        assertEquals(game.score(), 300);
    }


    /**
     * CASE 2
     * input : 5/5/5/5/5/5/5/5/5/5/5 (5 for first spare second and last 5) expected score equal to 150
     */

    @Test
    public void fiveAndSpareWithFinalFiveExpected_150() {

        String sequence = "5/5/5/5/5/5/5/5/5/5/5";
        game.setSequenceRoll(sequence);
        assertEquals(game.score(), 150);
    }


    /**
     * CASE 3
     * input : 9-9-9-9-9-9-9-9-9-9- (9 for first miss second) expected score equal to 90
     */

    @Test
    public void nineAndMissExpected_90() {

        String sequence = "9-9-9-9-9-9-9-9-9-9-";
        game.setSequenceRoll(sequence);
        assertEquals(game.score(), 90);
    }


    /**
     * CASE 4
     * input : 11111111111111111111 (20 one)  expected score equal to 20
     */

    @Test
    public void allOnesExpected_20() {

        String sequence = "11111111111111111111";
        game.setSequenceRoll(sequence);
        assertEquals(game.score(), 20);
    }


    /**
     * CASE 5
     * input : Invalid character expected IllegalArgumentException
     */

    @Test
    public void invalidCharacter() {

        String sequence = "Bowling Game";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            game.setSequenceRoll(sequence);
        });
        assertEquals(e.getMessage(), "Invalid character found in the sequence");
    }


    /**
     * CASE 6
     * input : Invalid spare expected IllegalArgumentException
     */

    @Test
    public void invalidSpare() {

        String sequence = "/5";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            game.setSequenceRoll(sequence);
        });
        assertEquals(e.getMessage(), "Invalid spare found in the sequence");
    }

}