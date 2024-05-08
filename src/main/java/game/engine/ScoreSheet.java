package game.engine;


import game.utilities.Color;
import game.creatures.Creature;
import game.realms.Realm;
import game.dice.*;

public class ScoreSheet {
    //We will create linked list of type realm/action and everytime we will update the linked list
    //--------------------------Attributes--------------------------//
    private Realm[] realms;
    private String string;
    //--------------------------Constructor--------------------------//
    public ScoreSheet(Realm[] realmsReference){
        this.realms=realmsReference;
    }

    //--------------------------Methods--------------------------//
    private void updateScoresheet(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("\n\nScoreSheet\n\n");
        int c=0;
        for(Realm realm:realms){
            stringBuilder.append(realm.toString());
        }
        string=stringBuilder.toString();
    }
    public void displayScoreSheet(){
        updateScoresheet();
        System.out.println(string);
    }
    public void displayRedRealm(){
        System.out.println(realms[Color.RED.ordinal()]);
    }
    @Override
    public String toString(){
        updateScoresheet();
        return string;
    }
    public Creature getCreatureByRealm(Dice dice){
        if(dice.getRealm()== Color.WHITE){
            System.err.println("There is no white realm");
            return null;
        }
        for(Realm i:realms){
            if(dice.getRealm().equals(i.getColor())) {
                return i.getCreature(dice);
            }
        }
        System.err.println("Creature not found");
        return null;
    }



}


