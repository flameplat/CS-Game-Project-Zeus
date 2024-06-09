package game.dice;

import game.utilities.GameColor;

public class RedDice extends Dice {
    private static final GameColor GAME_COLOR = GameColor.RED;
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private int dragonNumber;
    private DiceStatus status;
    private int value; // value of the dices

    // -----------------------constructor-----------------------//
    public RedDice(int value) {
        this.value = value;
        this.status = DiceStatus.AVAILABLE;
    }
    public RedDice(RedDice redDice){
        this.value = redDice.value;
        this.status = DiceStatus.AVAILABLE;
    }

    public RedDice() {
        this(1);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(RED + "%s(%d)" + RESET, GAME_COLOR, value);
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

    public void selectsDragon(int dragonNumber) {
        this.dragonNumber = dragonNumber;
    }

    //Should be used in the redRealm when getting creature by red die
    public int getDragonNumber() {
        return dragonNumber;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RedDice other = (RedDice) o;
        return (GAME_COLOR == other.getRealm()) && (value == other.getValue());
    }
}
