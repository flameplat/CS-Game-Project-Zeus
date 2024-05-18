package game.collectibles;

import game.utilities.Color;
import game.utilities.ColorComparator;

public class ColorBonus extends Collectibles implements Comparable<ColorBonus>{
    // -----------------------Attributes-----------------------//
    private final Color colorBonus;

    private static final String instruction="Color bonus makes you play a certain realm with any dice value you want";




    // -----------------------constructor-----------------------//
    public ColorBonus(Color color){
            this.colorBonus=color;
    }
    //-----------------------Methods-----------------------//

    @Override
    public String toString() {
        return colorBonus.toString().charAt(0)+"B";
    }

    public Color getColor() {
        return colorBonus;
    }
    public static String getInstruction(){
        return instruction;
    }
    @Override
    public int compareTo(ColorBonus o) {
        return new ColorComparator().compare(colorBonus, o.getColor());
    }
    public String getName(){
        return colorBonus+" BONUS";
    }
}
