package game.collectibles;

public class TimeWarp extends Collectibles {
    // -----------------------Attributes-----------------------//
    private static final String instruction = "The Time Warp power gives you the ability\nto roll the available dice again";

    // Initialize the TW to DISABLED
    public static String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return "TW";
    }

    public String getName() {
        return "Time Warp";
    }


}
