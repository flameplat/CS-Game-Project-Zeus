package game.collectibles;

public class ArcaneBoost extends Collectibles {
    // -----------------------Attributes-----------------------//
    private CollectiblesStatus status;
    private static final CollectiblesType type=CollectiblesType.ARCANE_BOOST;
    private static final String instruction="The Arcane Boost power gives you the ability\n" +
            "to play an extra round using one of the unselected dice";

    // -----------------------constructor-----------------------//
    // Initialize the AB to DISABLED
    public ArcaneBoost() {
        this.status = CollectiblesStatus.DISABLED;
    }

    // -----------------------Methods-----------------------//
    // Method to get current status of TW

    public CollectiblesStatus getStatus() {
        return status;
    }
    public CollectiblesType getType(){
        return type;
    }
    // Method to set current status of TW to ENABLED/USED
    /*
     * it becomes Enabled when the user is
     * rewarded with a power from a move or at the beginning of a round
     */
    public void setStatus(CollectiblesStatus status) {
        this.status = status;
    }
    public static String getInstruction(){
        return instruction;
    }

    @Override
    public String toString() {
        return "Arcane Boost: "+status.toString();
    }

}
