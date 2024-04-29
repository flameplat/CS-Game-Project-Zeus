package game.engine;

import game.realms.Realm;
import game.creatures.*;
import game.dice.Dice;


public class Move {
    // -----------------------Attributes-----------------------//
    private Realm realm;
    private Creature creature;
    private HitRegionsOfDragons hitRegion;
    private Dice dice;
    private int moveNumber;


    // -----------------------Constructor-----------------------//
    public Move(Realm realm, Creature creature) {
        this.realm = realm;
        this.creature = creature;
        hitRegion = null;
    }

    // constuctor for moves regarding the dragons
    public Move(Realm realm, Creature creature, HitRegionsOfDragons hitRegion) {
        this(realm, creature);
        this.hitRegion = hitRegion;

    }

    // -----------------------Methods-----------------------//
    public Realm getRealm() {
        return realm;
    }
    public int getMoveNumber(){
        return moveNumber;
    }
    public Creature getCreature() {
        return creature;
    }

    public HitRegionsOfDragons getHitRegion() {
        return hitRegion;
    }
    @Override
    public String toString(){
        return null;
    }

}
