package game.creatures;

public class Dragon extends Creature {
    // -----------------------Attributes-----------------------//
    private final int dragonNumber; // number of dragon
    private Object[] health; // [FACE,WING,TAIL,HEART]
    private int score; // score of the dragon
    private boolean isAlive; // status of the dragons if they are alive or dead

    // -----------------------Constructor-----------------------//

    public Dragon(Object[] hitValues, int score, int dragonNumber) {
        this.health = hitValues;
        isAlive = true;
        this.score = score;
        this.dragonNumber = dragonNumber;
    }
    public Dragon(Dragon dragon){
        this.health = new Object[dragon.health.length];
        System.arraycopy(dragon.health, 0, health, 0, health.length);
        isAlive = true;
        this.score = dragon.score;
        this.dragonNumber = dragon.dragonNumber;
    }

    public Dragon(int dragonNumber) {
        this.dragonNumber = dragonNumber;
    }

    public Dragon() {
        this.dragonNumber = 1;
    }

    // -----------------------Methods-----------------------//
    @Override
    public boolean isAlive() {
        // check wheather a certain dragon is alive or not\
        int is_Alive = 0;
        for (Object o : health) {
            if (!o.equals("X"))

                is_Alive += (int) o;
        }
        if (is_Alive == 0) {
            isAlive = false;
        }
        return isAlive;
    }

    @Override
    public boolean attack(int value, HitRegionsOfDragons region) {
        if (isAlive) {
            switch (region) {
                case FACE:
                    health[0] = "X";
                    break;
                case WING:
                    health[1] = "X";
                    break;

                case TAIL:
                    health[2] = "X";
                    break;
                case HEART:
                    health[3] = "X";
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public int getScore() { // getter for score of the dragon
        return score;
    }

    public int getDragonNumber() { // getter for the dragon number
        return dragonNumber;
    }

    @Override
    public String toString() {
        return "Dragon ";
    }


    public Object[] getHealth() {
        return health;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return dragonNumber == ((Dragon) o).dragonNumber;

    }

}
