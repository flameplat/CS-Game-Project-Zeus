package game.dice;

import game.Color;

public class Dice {
    // -----------------------Attributes-----------------------//
    private Color color; // [RED,GREEN,BLUE,MAGENTA,YELLOW,WHITE]
    private int value; // value of the dices

    // -----------------------constructor-----------------------//
    public Dice(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    // -----------------------Methods-----------------------//
    // getter for the dice value
    public int getDiceValue() {
        return value;
    }

    // getter for the dice color
    public Color getDiceColor() {
        return color;
    }
    @Override
    public String toString(){
        return null;
    }

}
