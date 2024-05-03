package game.collectibles;

public abstract class Collectibles {
    public CollectiblesStatus getStatus(){
        return null;
    }
    public void setStatus(CollectiblesStatus status){

    }
    public abstract CollectiblesType getType();
    @Override
    public abstract String toString();
}
