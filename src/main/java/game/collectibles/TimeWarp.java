package game.collectibles;

public class TimeWarp extends Collectibles {
    // -----------------------Attributes-----------------------//
    private CollectiblesStatus status;
    private static final String instruction="The Time Warp power gives you the ability\nto roll the available dice again";

    // -----------------------constructor-----------------------//
    // Initialize the TW to DISABLED
    public TimeWarp() {
        status = CollectiblesStatus.DISABLED;
    }

    // -----------------------methods-----------------------//
    // Method to get current status of TW
    @Override
    public CollectiblesStatus getStatus() {
        return status;
    }

    // Method to set current status of TW to ENABLED/USED
    /*
     * it becomes Enabled when the user is
     * rewarded with a power from a move or at the beginning of a round
     */
    @Override
    public void setStatus(CollectiblesStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Time Warp";
    }
    public static String getInstruction(){
        return instruction;
    }

}
