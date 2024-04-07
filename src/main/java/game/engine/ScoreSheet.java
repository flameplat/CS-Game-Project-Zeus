package game.engine;


import game.Realms.Realm;

public class ScoreSheet {
    //We will create linked list of type realm/action and everytime we update the linked list
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

}


