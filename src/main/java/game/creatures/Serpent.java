
package game.creatures;

public class Serpent extends Creature{
    public Serpent (int heads){
        //constructor that creates a linked list of heads 
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
