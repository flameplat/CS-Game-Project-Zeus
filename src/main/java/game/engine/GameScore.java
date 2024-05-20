package game.engine;

import game.realms.*;
import game.utilities.Color;


public class GameScore {
    //--------------------------Attributes--------------------------//
    // all of the scores for the Realms
    private int totalElementalCrests;
    private int totalScore;
    private final String playerName;
    private final Realm[] realms;
    //--------------------------Constructor--------------------------//
    public GameScore(Realm[] realms, String playerName) {
        this.playerName=playerName;
        this.realms=realms;
        totalElementalCrests = 0;
        totalScore = 0;
    }
    public GameScore(){
        this.realms=new Realm[]{new RedRealm(),new GreenRealm(),new BlueRealm(),new MagentaRealm(),new YellowRealm()};
        this.playerName="NULL";
        totalElementalCrests = 0;
        totalScore = 0;
    }
    //--------------------------Methods--------------------------//


    public void updateGameScore(){
        totalScore=0;
        for (Realm realm : realms) {
            totalScore += realm.getTotalScore();
        }
    }
    public void receiveElementalCrest(){
        totalElementalCrests++;
    }
    public int getYellowRealmScore() {
        return realms[Color.YELLOW.ordinal()].getTotalScore();
    }

    public int getGreenRealmScore() {
        return realms[Color.GREEN.ordinal()].getTotalScore();
    }

    public int getRedRealmScore() {
        return realms[Color.RED.ordinal()].getTotalScore();
    }

    public int getMagentaRealmScore() {
        return realms[Color.MAGENTA.ordinal()].getTotalScore();
    }

    public int getBlueRealmScore() {
        return realms[Color.BLUE.ordinal()].getTotalScore();
    }
    public void setTotalScore(int score){
        this.totalScore=score;
    }

    public int getTotalElementalCrests() {
        return totalElementalCrests;
    }
    public int getCurrentScore(){
        updateGameScore();
        return totalScore;
    }
    public int getTotalScore() {
        updateGameScore();
        int minScore=realms[0].getTotalScore();
        for(int i=0;i<5;i++){
            if(realms[i].getTotalScore()<minScore){
                minScore=realms[i].getTotalScore();
            }
        }
        totalScore+=totalElementalCrests*minScore;
        return totalScore;
    }
    private int getTotalScoreForColor(Color color) {
        return realms[color.ordinal()].getTotalScore();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        // Player Name row
        sb.append(String.format("%-20s", "Player Name:")).append(playerName).append("\n");

        // Realm headers row
        sb.append(String.format("%-20s", "Realm"));
        sb.append(String.format("%-20s", "Elemental Crests"));
        sb.append(String.format("%-20s", "Total Score")).append("\n");

        // Add rows for each realm
        //RED, GREEN, BLUE, MAGENTA, YELLOW, WHITE
        String[] colors = {"Red", "Green", "Blue", "Magenta", "Yellow"};
        for (int i = 0; i < 5; i++) {
            sb.append(String.format("%-20s", colors[i] + " Realm"));
            sb.append(String.format("%-20d", realms[i].getNoElementalCrests()));
            sb.append(String.format("%-20d", getTotalScoreForColor(Color.values()[i]))).append("\n");
        }
        sb.append("-".repeat(45)).append("\n");

        // Total Elemental Crests row
        sb.append(String.format("%-40s", "Total Elemental Crests")).append(getTotalElementalCrests()).append("\n");

        // Final Score row
        sb.append(String.format("%-40s", "Final Score")).append(getTotalScore()).append("\n");

        return sb.toString();
    }
}
