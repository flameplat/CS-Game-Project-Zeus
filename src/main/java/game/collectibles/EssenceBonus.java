package game.collectibles;

public class EssenceBonus extends Collectibles {
    private static final String instruction = "Essence bonus gives you the ability to play any realm you want with any dice value";

    public static String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return "EB";
    }

    public String getName() {
        return "Essence Bonus";
    }


}
