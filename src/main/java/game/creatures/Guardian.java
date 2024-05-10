package game.creatures;

public class Guardian extends Creature{
    private boolean status;
    private int score;
    public Guardian(){
        this.status=true;
        this.score=0;
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
        return 'Gaia Guardian';
    }
}
