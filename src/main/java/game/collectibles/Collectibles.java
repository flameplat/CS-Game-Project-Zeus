package game.collectibles;

import game.utilities.GameColor;

import java.util.HashMap;
import java.util.Map;

public abstract class Collectibles {
    private static final Map<String, Integer> collectibleCounters = new HashMap<>();

    public static Collectibles getCollectibleFromString(String reward) {
        if (reward == null) {
            return null;
        }

        collectibleCounters.put(reward, collectibleCounters.getOrDefault(reward, 0) + 1);

        switch (reward.toLowerCase()) {
            case "timewarp":
                return new TimeWarp();
            case "arcaneboost":
                return new ArcaneBoost();
            case "essencebonus":
                return new EssenceBonus();
            case "redbonus":
                return new ColorBonus(GameColor.RED);
            case "bluebonus":
                return new ColorBonus(GameColor.BLUE);
            case "greenbonus":
                return new ColorBonus(GameColor.GREEN);
            case "magentabonus":
                return new ColorBonus(GameColor.MAGENTA);
            case "yellowbonus":
                return new ColorBonus(GameColor.YELLOW);
            case "elementalcrest":
                return new ElementalCrest();
            default:
                return null;
        }
    }

    public static int getCounter(String reward) {
        return collectibleCounters.getOrDefault(reward, 0);
    }

    @Override
    public abstract String toString();

    public abstract String getName();
}
