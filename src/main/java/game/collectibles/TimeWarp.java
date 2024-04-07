package game.collectibles;

public class TimeWarp extends Collectibles{
    private CollectiblesStatus status;
    //Initialize the TW to DISABLED
    public TimeWarp(){
        status=CollectiblesStatus.DISABLED;
    }
    //Method to get current status of TW
    public CollectiblesStatus getStatus(){
        return status;
    }
    //Method to set current status of TW to ENABLED/USED
    /*it becomes Enabled when the user is
     rewarded with a power from a move or at the beginning of a round
     */
    public void setStatus(CollectiblesStatus status){
        this.status=status;
    }

}
