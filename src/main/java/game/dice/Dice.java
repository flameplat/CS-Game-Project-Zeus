package game.dice;

import game.utilities.Color;

public abstract class Dice {

    //-----------------------Attributes-----------------------//

    //-------------------------Methods------------------------//
    public abstract void setValue(int value);
    // getter for the dice value
    public abstract int getValue();
    public abstract Color getRealm();
    public abstract DiceStatus getDiceStatus();
    public abstract void setDiceStatus(DiceStatus status);
    public static Dice getNewDice(Color color,int value){
        switch (color){
            case RED:return new RedDice(value);
            case GREEN:return new GreenDice(value);
            case BLUE:return new BlueDice(value);
            case MAGENTA:return new MagentaDice(value);
            case YELLOW:return new YellowDice(value);
            case WHITE:return new WhiteDice(value);
        }
        return null;
    }

}
