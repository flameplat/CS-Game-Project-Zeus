package game.collectibles;

import game.Color;

public class ColorBonus extends Collectibles {
    // -----------------------Attributes-----------------------//
    private Color colorBonus;
    private CollectiblesStatus status;
    private static final CollectiblesType type=CollectiblesType.COLOR_BONUS;
    private static final String instruction="Color bonus makes you play a certain realm with any dice value you want";




    // -----------------------constructor-----------------------//
    public ColorBonus(Color color){
            this.colorBonus=color;
            this.status=CollectiblesStatus.DISABLED;
    }
    //-----------------------Methods-----------------------//
    public CollectiblesType getType(){
        return type;
    }
    @Override
    public CollectiblesStatus getStatus() {
        return status;
    }
    @Override
    public void setStatus(CollectiblesStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return colorBonus+"_BONUS";
    }

    public Color getColor() {
        return colorBonus;
    }
    public static String getInstruction(){
        return instruction;
    }
}
