package game.collectibles;

import game.utilities.GameColor;

import java.util.HashMap;
import java.util.Map;

public abstract class Collectibles {
    /**
     * This map stores the count of collectible items.
     * The keys are strings representing the collectible items,
     * and the values are integers representing the count of each collectible item.
     */
    private static final Map<String, Integer> collectibleCounters = new HashMap<>();

    /**
     * Converts a string representation of a collectible into an instance of the corresponding Collectibles class.
     *
     * @param reward The string representation of the collectible.
     * @return An instance of the corresponding Collectibles class, or null if the reward is invalid or null.
     */
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
    /**
     * Retrieves the counter value for a specific reward.
     *
     * @param reward The reward for which to retrieve the counter value.
     * @return The counter value for the specified reward. Returns 0 if the reward is not found.
     */
    public static int getCounter(String reward) {
        return collectibleCounters.getOrDefault(reward, 0);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public abstract String toString();

    /**
     * Retrieves the name of the collectible item.
     *
     * @return The name of the collectible item as a String.
     */
    public abstract String getName();
}
