package com.ab.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bowling Game given a Roll and Frame provide the score of game from a String input
 *
 * @author Atef BECHEIKH
 */

public class BowlingGame {
    private Map<Integer, List<Roll>> playerFrames;


    public void setSequenceRoll(String sequenceRoll) {
        playerFrames = new HashMap<>();
        int numFrames = 0;
        boolean frameEnd = false;
        List<Roll> rolls = new ArrayList<>();
        for (int i = 0; i < sequenceRoll.length(); i++) {
            Roll roll = new Roll(sequenceRoll.charAt(i));
            if (roll.isSpare() && rolls.size() == 0)
                throw new IllegalArgumentException("Invalid spare found in the sequence");

            if (rolls.size() > 0)
                roll.setFirstRoll(rolls.get(0));

            rolls.add(roll);

            if (numFrames < 9) {
                if (roll.isStrike())
                    frameEnd = true;

                if (frameEnd) {
                    this.playerFrames.put(numFrames, rolls);
                    rolls = new ArrayList<>();
                    numFrames++;
                }

                frameEnd = !frameEnd;
            } else if (i == sequenceRoll.length() - 1)
                this.playerFrames.put(numFrames, rolls);
        }
    }


    /**
     * Calculate the score of 10 Frames
     * for each frame
     * check if strike
     * result is 10 plus the sum of the next two Rolls knocked pins
     * check if spare
     * result is 10 plus the next Roll knocked pins
     * else
     * result of then current frame
     *
     * @return score of a bowling game
     */

    public int score() {
        System.out.print("Frame :         ");
        for (int i = 0; i < this.playerFrames.size(); i++) {
            System.out.print(i + 1 + "     ");
        }
        System.out.println();

        System.out.print("Player :       |");
        for (int i = 0; i < this.playerFrames.size(); i++) {
            for (Roll roll : this.playerFrames.get(i)) {
                if (roll.isStrike()) {
                    if (i < 9)
                        System.out.print(roll.getPin() + "| |");
                    else
                        System.out.print(roll.getPin() + "|");
                } else
                    System.out.print(roll.getPin() + "|");
            }
            if (i != this.playerFrames.size() - 1)
                System.out.print(" |");
        }
        System.out.println();

        System.out.print("Score :         ");
        int frameScore;
        int totalScore = 0;
        List<Roll> bowlingFrame;
        for (int i = 0; i < this.playerFrames.size(); i++) {
            frameScore = 0;
            bowlingFrame = this.playerFrames.get(i);
            if (bowlingFrame.size() > 1) {
                frameScore = bowlingFrame.stream().mapToInt(roll -> roll.numberOfKnockedPins()).sum();
                // check if spare score is 10 plus the next Roll knocked pins
                if (i < 9 && bowlingFrame.get(1).isSpare())
                    frameScore += this.playerFrames.get(i + 1).get(0).numberOfKnockedPins();
                // check if strike score is 10 plus the sum of the next two Rolls knocked pins
            } else if (bowlingFrame.get(0).isStrike()) {
                if (i < 9) {
                    frameScore = bowlingFrame.get(0).numberOfKnockedPins();
                    if (this.playerFrames.get(i + 1).size() > 1)
                        frameScore += this.playerFrames.get(i + 1).get(0).numberOfKnockedPins() + this.playerFrames.get(i + 1).get(1).numberOfKnockedPins();
                    else
                        frameScore += this.playerFrames.get(i + 1).get(0).numberOfKnockedPins() + this.playerFrames.get(i + 2).get(0).numberOfKnockedPins();
                } else {
                    frameScore = bowlingFrame.stream().mapToInt(roll -> roll.numberOfKnockedPins()).sum();
                }
            }
            totalScore += frameScore;
            System.out.print("|" + totalScore + "|  ");
        }
        System.out.println();
        System.out.println("Total score : " + totalScore);
        return totalScore;
    }

}