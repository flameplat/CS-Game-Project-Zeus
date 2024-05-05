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


}
