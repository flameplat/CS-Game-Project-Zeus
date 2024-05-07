package game.creatures;

public class Guardian extends Creature{
    private int score;
    private boolean status;

    public Guardian(int score){
        this.score=score;
        this.status=true;
    }
    public Guardian(){
        this(1);
    }
    @Override
    public boolean attack() {
        if(isAlive()){
            status=false;
            return true;
        }
        return false;
    }
    @Override
    public boolean isAlive() {
        return status;
    }

    @Override
    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        String string;
        if(isAlive()){
            return String.valueOf(score);
        }
        return "X";

    }
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }
}
