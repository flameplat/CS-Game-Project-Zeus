package game.dice;

import game.utilities.GameColor;

public class WhiteDice extends Dice {
    private static final GameColor GAME_COLOR = GameColor.WHITE;
    private DiceStatus status;
    private int value; // value of the dices

    // -----------------------constructor-----------------------//
    public WhiteDice(int value) {
        this.value = value;
        this.status = DiceStatus.AVAILABLE;
    }
    public WhiteDice(WhiteDice whiteDice){
        this.value = whiteDice.value;
        this.status = DiceStatus.AVAILABLE;
    }

    public WhiteDice() {
        this(1);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format("%s(%d)", GAME_COLOR, value);
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
        WhiteDice other = (WhiteDice) o;
        return (GAME_COLOR == other.getRealm()) && (value == other.getValue());
    }
    public String getName(){
        return String.format("%s(%d)", GAME_COLOR, value);
    }
}
