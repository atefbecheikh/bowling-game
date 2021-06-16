package com.ab.bowling;

/**
 * Bowling Roll defined by number of knocked pins
 *
 * @author Atef BECHEIKH
 */

public class Roll {
    private char pin;
    private Roll firstRoll;

    public Roll(char pin) {
        this.isValid(pin);
        this.pin = pin;
    }

    public char getPin() {
        return pin;
    }

    public void setFirstRoll(Roll previousRoll) {
        this.firstRoll = previousRoll;
    }

    public int numberOfKnockedPins() {
        switch (Character.toUpperCase(this.pin)) {
            case 'X':
                return 10;
            case '/':
                return 10 - this.firstRoll.numberOfKnockedPins();
            case '-':
                return 0;
            default:
                return Integer.parseInt(String.valueOf(this.pin));
        }
    }

    public boolean isStrike() {
        return Character.toUpperCase(this.pin) == 'X';
    }

    public boolean isSpare() {
        return this.pin == '/';
    }

    private void isValid(char pin) {
        String authorizedCharacter = "-/X123456789";
        if (authorizedCharacter.indexOf(Character.toUpperCase(pin)) == -1)
            throw new IllegalArgumentException("Invalid character found in the sequence");
    }

}