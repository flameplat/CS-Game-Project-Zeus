package game.dice;

import game.utilities.GameColor;

public class MagentaDice extends Dice {
    private static final String RESET = "\u001B[0m";
    private static final String MAGENTA = "\u001B[35m";
    private static final GameColor GAME_COLOR = GameColor.MAGENTA;
    private DiceStatus status;
    private int value; // value of the dices

    // -----------------------constructor-----------------------//
    public MagentaDice(int value) {
        this.value = value;
        this.status = DiceStatus.AVAILABLE;

    }
    public MagentaDice(MagentaDice magentaDice){
        this.value = magentaDice.value;
        this.status = DiceStatus.AVAILABLE;
    }

    public MagentaDice() {
        this(1);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(MAGENTA + "%s(%d)" + RESET, GAME_COLOR, value);
    }
    public String getName(){
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
        MagentaDice other = (MagentaDice) o;
        return (GAME_COLOR == other.getRealm()) && (value == other.getValue());
    }
}
