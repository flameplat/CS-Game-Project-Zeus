package game.dice;

import game.Color;

public class GreenDice extends Dice{
    private static final Color color=Color.GREEN;
    private int value; // value of the dices
    // -----------------------constructor-----------------------//
    public GreenDice(int value) {
        this.value = value;
    }
    public GreenDice(){
        this.value=0;
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
        return null;
    }

    @Override
    public void setDiceStatus(DiceStatus status) {

    }

}
