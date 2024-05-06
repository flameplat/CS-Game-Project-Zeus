package game.creatures;

public class Guardian extends Creature{
    public Guardian(int score){

    }
    @Override
    public boolean attack(int value) {
        return false;
    }
    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }


    @Override
    public String toString() {
        return null;
    }
}
