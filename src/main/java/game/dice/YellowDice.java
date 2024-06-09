package game.dice;

import game.utilities.GameColor;

public class YellowDice extends Dice {
    private static final GameColor GAME_COLOR = GameColor.YELLOW;
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private DiceStatus status;
    private int value; // value of the dices

    // -----------------------constructor-----------------------//
    public YellowDice(int value) {
        this.value = value;
        this.status = DiceStatus.AVAILABLE;
    }
    public YellowDice(YellowDice yellowDice){
        this.value = yellowDice.value;
        this.status = DiceStatus.AVAILABLE;
    }

    public YellowDice() {
        this(1);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(YELLOW + "%s(%d)" + RESET, GAME_COLOR, value);
    }

    // getter for the dice value
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GameColor getRealm() {
        return GAME_COLOR;
    }

    @Override
    public DiceStatus getDiceStatus() {
        return status;
    }

    @Override
    public void setDiceStatus(DiceStatus status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        YellowDice other = (YellowDice) o;
        return (GAME_COLOR == other.getRealm()) && (value == other.getValue());
    }
    public String getName(){
        return String.format("%s(%d)", GAME_COLOR, value);
    }
}
