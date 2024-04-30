package game.engine;


import game.creatures.Creature;
import game.realms.Realm;
import game.dice.*;

public class ScoreSheet {
    //We will create linked list of type realm/action and everytime we will update the linked list
    //--------------------------Attributes--------------------------//
    private Realm[] realms;
    //--------------------------Constructor--------------------------//
    public ScoreSheet(Realm[] realmsReference){
        this.realms=realmsReference;
    }
    //--------------------------Methods--------------------------//
    boolean updateScoresheet(){
        return false;
    }
    void displayScoreSheet(){

    }
    @Override
    public String toString(){
        return null;
    }
    public Creature getCreatureByRealm(Dice dice){
        for(Realm i:realms){
            if(dice.getRealm().equals(i.getColor())) {
                return i.getCreature(dice);
            }
        }
        return null;
    }


}


