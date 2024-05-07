package game.dice;

import game.utilities.Color;
import org.testng.internal.EclipseInterface;

public class MagentaDice extends Dice{
    private DiceStatus status;
    private static final String RESET = "\u001B[0m";
    private static final String MAGENTA = "\u001B[35m";
    private static final Color color=Color.MAGENTA;
    private int value; // value of the dices
    // -----------------------constructor-----------------------//
    public MagentaDice(int value) {
        this.value = value;
        this.status=DiceStatus.AVAILABLE;

    }

    public MagentaDice(){
        this(0);
    }

    // -----------------------Methods-----------------------//

    // getter for the dice color
    @Override
    public String toString() {
        return String.format(MAGENTA+"%s(%d)"+RESET, color, value);
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
        MagentaDice other = (MagentaDice) o;
        return (color==other.getRealm())&&(value==other.getValue());
    }
}
