
package game.creatures;

public class Serpent extends Creature{
    private boolean status =true;
    private int headNumber = 1;
    private int score = 0;

    public Serpent (int headNumber,boolean status ){
        this.status=status;
        this.headNumber=headNumber;
    }
    public Serpent(Serpent serpent){
        this.status=serpent.status;
        this.headNumber=serpent.headNumber;
    }
    public Serpent(){
        this.status=true;
    }
    public boolean attack(int value) {
        return false;
    }

    @Override
    public boolean isAlive() {
        return status;
    }

    public int getHeadNumber(){
        return headNumber;
    }

    @Override
    public int getScore() {
        return score;
    }
    @Override
    public String toString() {
        return null;
    }
    public void setStatus(boolean status){
        this.status=status;
    }
    public void setHeadNumber(int headNumber){//setter//
        this.headNumber=headNumber;
    }
}