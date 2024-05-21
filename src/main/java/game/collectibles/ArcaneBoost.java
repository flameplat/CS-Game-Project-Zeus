package game.collectibles;

public class ArcaneBoost extends Collectibles {
    // -----------------------Attributes-----------------------//
    private static final String instruction = "The Arcane Boost power gives you the ability\n" +
            "to play an extra round using one of the unselected dice";

    // -----------------------constructor-----------------------//
    public ArcaneBoost() {
    }

    // -----------------------Methods-----------------------//
    public static String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return "AB";
    }

    public String getName() {
        return "Arcane Boost";
    }

}
