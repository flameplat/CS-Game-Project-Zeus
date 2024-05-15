package game.collectibles;

import game.utilities.Color;

public abstract class Collectibles {
    public static Collectibles getCollectibleFromString(String reward) {
        if(reward==null){
            return null;
        }
        switch (reward.toLowerCase()) {
            case "timewarp":
                return new TimeWarp();
            case "arcaneboost":
                return new ArcaneBoost();
            case "essencebonus":
                return new EssenceBonus();
            case "redbonus":
                return new ColorBonus(Color.RED);
            case "bluebonus":
                return new ColorBonus(Color.BLUE);
            case "greenbonus":
                return new ColorBonus(Color.GREEN);
            case "magentabonus":
                return new ColorBonus(Color.MAGENTA);
            case "yellowbonus":
                return new ColorBonus(Color.YELLOW);
            case "elementalcrest":
                return new ElementalCrest();
            default:
                return null;
        }
    }

    @Override
    public abstract String toString();
}
