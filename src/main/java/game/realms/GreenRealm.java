package game.realms;

import game.Color;
import game.collectibles.Collectibles;
import game.creatures.Creature;
import game.engine.Move;

public class GreenRealm extends Realm{
    // -----------------------Attributes-----------------------//
    // -----------------------Constructor-----------------------//
    // -----------------------Methods-----------------------//
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public Collectibles getReward() {
        return null;
    }

    @Override
    public boolean checkReward() {
        return false;
    }

    @Override
    public boolean attack(Move move) {
        return false;
    }

    @Override
    public int getTotalScore() {
        return 0;
    }

    @Override
    public int getNoElementalCrests() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Creature[] getAliveCreatures() {
        return new Creature[0];
    }
}
