package game.creatures;

public class Guardian extends Creature {
    private final int score;
    private boolean status;

    public Guardian(int score) {
        this.score = score;
        this.status = true;
    }

    public Guardian() {
        this(1);
    }
    public Guardian(Guardian guardian){
        this.score = guardian.score;
        this.status = true;
    }
    @Override
    public boolean attack() {
        if (isAlive()) {
            status = false;
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
        if (isAlive()) {
            return String.valueOf(score);
        }
        return "X";

    }

    public String getName() {
        String color;
        if (status) {
            color = "\u001B[32m";
        } else {
            color = "\u001B[37m";
        }
        return color + "Gaia " + score + "\u001B[0m";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return score == ((Guardian) o).score;
    }
}
