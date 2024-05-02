package game.collectibles;

public abstract class Collectibles {
    public CollectiblesStatus getStatus(){
        return null;
    }
    public void setStatus(CollectiblesStatus status){

    }
    @Override
    public abstract String toString();
}
