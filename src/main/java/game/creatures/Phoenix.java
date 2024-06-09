package game.creatures;

public class Phoenix extends Creature {
    private boolean status;
    private int totalHits;

    public Phoenix() {
        this.status = true;
        totalHits = 0;
    }
    public Phoenix(Phoenix phoenix){
        this.status = true;
        totalHits = 0;
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
        return "Phoenix";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }
}
