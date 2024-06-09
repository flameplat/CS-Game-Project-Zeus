package game.creatures;

public class Lion extends Creature {
    private boolean status;
    private int totalHits;

    public Lion() {
        this.status = true;
        this.totalHits = 0;
    }
    public Lion(Lion lion){
        this.status=true;
        this.totalHits=lion.totalHits;
    }

    @Override
    public boolean attack() {
        if (isAlive()) {
            totalHits++;
            if (totalHits == 11) {
                status = false;
            }
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
        return 0;
    }


    @Override
    public String toString() {
        return "Lion";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }

}
