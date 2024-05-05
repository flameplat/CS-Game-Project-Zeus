package game.dice;

import game.utilities.Color;

public class YellowDice extends Dice{
    private DiceStatus status;
    private static final Color color=Color.YELLOW;
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private int value; // value of the dices
    // -----------------------constructor-----------------------//
    public YellowDice(int value) {
        this.value = value;
        this.status=DiceStatus.AVAILABLE;
    }
    public YellowDice(){
        this.value=0;
        this.status=DiceStatus.AVAILABLE;
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(YELLOW+"%s(%d)"+RESET, color, value);
    }

    public void setValue(int value){
        this.value=value;
    }
    // getter for the dice value
    public int getValue() {
        return value;
    }
    public Color getRealm(){
        return color;
    }

    @Override
    public DiceStatus getDiceStatus() {
        return status;
    }

    @Override
    public void setDiceStatus(DiceStatus status) {
        this.status=status;
    }
}
