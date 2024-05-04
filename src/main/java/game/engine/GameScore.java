package game.engine;

import game.Color;
import game.realms.Realm;

public class GameScore {
    //--------------------------Attributes--------------------------//
    // all of the scores for the Realms
    private int totalElementalCrests;
    private int totalScore;
    private String playerName;
    private Realm[] realms;

    //--------------------------Constructor--------------------------//
    public GameScore(Realm[] realms,String playerName) {
        this.playerName=playerName;
        this.realms=realms;
        totalElementalCrests = 0;
        totalScore = 0;
    }
    //--------------------------Methods--------------------------//


    public void updateGameScore(){
        //Reset attributes to recalculate them
        totalScore=0;
        totalElementalCrests=0;
        for (int i = 0; i < realms.length; i++) {
            totalScore += realms[i].getTotalScore();
            totalElementalCrests+=realms[i].getNoElementalCrests();
        }
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

    public int getFinalScore(){
        int minScore=0;
        for(int i=0;i<5;i++){
            if(realms[i].getTotalScore()<minScore){
                minScore=realms[i].getTotalScore();
            }
        }
        totalScore+=totalElementalCrests*minScore;
        return totalScore;
    }

    public int getTotalElementalCrests() {
        return totalElementalCrests;
    }

    public int getTotalScore() {
        return totalScore;
    }
    private int getTotalScoreForColor(Color color) {
        return realms[color.ordinal()].getTotalScore();
    }

    @Override
    public String toString(){
        updateGameScore();
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
        sb.append(String.format("%-40s", "Total Elemental Crests")).append(totalElementalCrests).append("\n");

        // Final Score row
        sb.append(String.format("%-40s", "Final Score")).append(getFinalScore()).append("\n");

        return sb.toString();
    }
}
