package game.dice;

import game.Color;

public class RedDice extends Dice{
    private static final Color color=Color.RED;
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private int dragonNumber;
    private DiceStatus status;
    private int value; // value of the dices
    // -----------------------constructor-----------------------//
    public RedDice(int value) {
        this.value = value;
        this.status=DiceStatus.AVAILABLE;
    }
    public RedDice(){
        this.value=0;
        this.status=DiceStatus.AVAILABLE;
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(RED+"%s(%d)"+RESET, color, value);
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
    public void selectsDragon(int dragonNumber){
        this.dragonNumber=dragonNumber;
    }
    public int getDragonNumber(){
        return dragonNumber;
    }
}
