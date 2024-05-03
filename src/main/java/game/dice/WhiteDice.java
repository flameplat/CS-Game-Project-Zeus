package game.dice;

import game.Color;

public class WhiteDice extends Dice{
    private DiceStatus status;
    private static final Color color=Color.WHITE;
    private int value; // value of the dices
    // -----------------------constructor-----------------------//
    public WhiteDice(int value){
        this.value = value;
        this.status=DiceStatus.AVAILABLE;
    }
    public WhiteDice(){
        this.value=0;
        this.status=DiceStatus.AVAILABLE;
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format("%s(%d)", color, value);
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
