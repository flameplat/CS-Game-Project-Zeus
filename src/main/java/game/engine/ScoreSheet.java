package game.engine;


import game.Realms.Realm;

public class ScoreSheet {
    /* • Both players tally their scores based on the creatures subdued and Elemental Crests
    collected in each realm.
        • The wizard with the highest score is declared the Protector of Eldoria.
     */
    //Keep track of: dead creatures and alive creatures in each realm.
    private Realm[] realms;
    public ScoreSheet(Realm[] realmsReference){
        this.realms=realmsReference;
    }
    boolean updateScoresheet(){
        return false;
    }
    void displayScoreSheet(){
    }

}


