package game.dice;
import game.Color;
public class Dice {
    private Color color;
    private int value;
    /*[RED,GREEN,BLUE,MAGENTA,YELLOW,WHITE]
    ->Rolls
     [RED(1),GREEN(3),BLUE(2),MAGENTA(4),YELLOW(5),WHITE(6)]
    ->Choose a die Green(3)
    ->Player attacks Green Realm
    ->All dices with values less than Green(3) are pushed to Forgotten Realm
    ->Forgotten Realm [RED(1),
     */
    public Dice(Color color,int value){
        this.color=color;
        this.value=value;
    }
    public int getDiceValue(){
        return value;
    }
    public Color getDiceColor(){
        return color;
    }

}
