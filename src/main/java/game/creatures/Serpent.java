package game.creatures;

public class Serpent extends Creature {
    private boolean status;
    private int headNumber;
    private int id;
    private int totalHits;

    public Serpent(int id, int headNumber) {
        this.status = true;
        this.headNumber = headNumber;
        this.totalHits = 0;
        this.id = id;
    }

    public Serpent() {
        this.status = true;
    }

    @Override
    public boolean attack() {
        if (isAlive()) {
            totalHits++;
            if (totalHits == headNumber) {
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
        return "Hydra Serpent " + id;
    }

    public int getSerpentNumber() {
        return id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }
}
