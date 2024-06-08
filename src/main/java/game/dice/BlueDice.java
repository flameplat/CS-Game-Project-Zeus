package game.dice;

import game.utilities.GameColor;

public class BlueDice extends Dice {
    private static final GameColor GAME_COLOR = GameColor.BLUE;
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private DiceStatus status;

    private int value; // value of the dices

    // -----------------------constructor-----------------------//
    public BlueDice(int value) {
        this.value = value;
        this.status = DiceStatus.AVAILABLE;

    }
    public BlueDice(BlueDice blueDice){
        this.value = blueDice.value;
        this.status = DiceStatus.AVAILABLE;
    }
    public BlueDice() {
        this(1);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(BLUE + "%s(%d)" + RESET, GAME_COLOR, value);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlueDice other = (BlueDice) o;
        return (GAME_COLOR == other.getRealm()) && (value == other.getValue());
    }
    public String getName(){
        return String.format("%s(%d)", GAME_COLOR, value);
    }
}
