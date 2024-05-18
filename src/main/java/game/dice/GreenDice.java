package game.dice;

import game.utilities.Color;

public class GreenDice extends Dice{
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final Color color=Color.GREEN;
    private int value; // value of the dices
    private DiceStatus status;
    // -----------------------constructor-----------------------//
    public GreenDice(int value) {
        this.value = value;
        this.status=DiceStatus.AVAILABLE;
    }
    public GreenDice(){
        this(1);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(GREEN+"%s(%d)"+RESET, color, value);
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
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GreenDice other = (GreenDice) o;
        return (color==other.getRealm())&&(value==other.getValue());
    }

}
