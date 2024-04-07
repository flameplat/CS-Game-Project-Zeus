package game.collectibles;

public abstract class Collectibles {
    public CollectiblesStatus getStatus(){
        return null;
    }
    public void setStatus(CollectiblesStatus status){

    }
    public boolean isBonus(){
        return false;
    }
    @Override
    public abstract String toString();
}
