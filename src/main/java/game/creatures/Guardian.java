package game.creatures;

public class Guardian extends Creature{
    private int score;
    private boolean status;
    public Guardian(){
        this.score = 0;
        this.status = true;
    }
    @Override
    public boolean attack() {
        if(isAlive()){
            status = false;
            return true;
        }
        else
            return false;
    }
    @Override
    public boolean isAlive() {
        return status;
    }

    @Override
    public int getScore() {
        return 0;
    }


    @Override
    public String toString() {
        return "Gaia Guardian";
    }
}
