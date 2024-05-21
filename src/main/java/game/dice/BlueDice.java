package game.dice;

import game.utilities.Color;

public class BlueDice extends Dice {
    private static final Color color = Color.BLUE;
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private DiceStatus status;

    private int value; // value of the dices

    // -----------------------constructor-----------------------//
    public BlueDice(int value) {
        this.value = value;
        this.status = DiceStatus.AVAILABLE;

    }

    public BlueDice() {
        this(1);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(BLUE + "%s(%d)" + RESET, color, value);
    }

    // getter for the dice value
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getRealm() {
        return color;
    }

    @Override
    public DiceStatus getDiceStatus() {
        return status;
    }

    @Override
    public void setDiceStatus(DiceStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlueDice other = (BlueDice) o;
        return (color == other.getRealm()) && (value == other.getValue());
    }
}
