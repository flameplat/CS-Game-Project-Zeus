package game.realms;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/* need to get the toString method
 * need to make a description of how every code works and does
 * need to see how can i return a printed list in method geetRealmmoves instead of a refrence
 */


import game.utilities.GameColor;
import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.ElementalCrest;
import game.collectibles.EssenceBonus;
import game.collectibles.TimeWarp;
import game.creatures.Creature;
import game.creatures.Serpent;
import game.engine.Move;
import game.dice.*;

import java.util.LinkedList;

public class BlueRealm extends Realm{
    // -----------------------Attributes-----------------------//
    /*private int totalRealmScore;*/
    private int hitcount;
    private int noElementalCrests;
    private final int S1HeadNumber=5;
    private final int S2HeadNumber=6;
    private final Serpent[] serpent1;
    private final Serpent[] serpent2;
    private final Collectibles[] rewardProperties;
    private static final String name="Tide Abyss";
    private int[] scoreProperties;
    private final int[] scoreSheetValues;
    private static final GameColor REALM_GAME_COLOR = GameColor.BLUE;
    private final String[] attackValues;
    private final String[] rewardValues;
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";


    /**
     * Represents a BlueRealm.
     */
    //-----------------------Constructor-----------------------//
    public BlueRealm() {
        serpent1 =new Serpent [S1HeadNumber];

        for(int i=0; i<S1HeadNumber;i++){
            serpent1[i] = new Serpent (i,true);
            serpent1[i].setHeadNumber(i+1);
        }
        serpent2 =new Serpent [S2HeadNumber];
        for(int j=0; j<S2HeadNumber;j++){
            serpent2[j] = new Serpent (j,true);
            serpent2[j].setHeadNumber(j+1);
        }
        attackValues=new String[11];
        for(int k=0;k<11;k++){
            attackValues[k]="---";
        }
        rewardValues=new String[11];
        scoreSheetValues=new int[11];

        rewardProperties=getRewardsProperties();
        scoreProperties=getScoreProperties();
        getScoreScoreSheet();
    }


    /**
     * Returns the name of the realm as a String.
     *
     * @return The name of the realm.
     */
    // -----------------------Methods-----------------------//
    @Override
    public String getName() {
        return  (BLUE + "Blue Realm" + RESET);
    }

    /**
     * This method returns the color of the realm.
     *
     * @return The color of the realm.
     */
    @Override
    public GameColor getColor() {
        return REALM_GAME_COLOR;
    }

    /**
     * Returns the status of the realm.
     *
     * @return the status of the realm as an integer
     */
    @Override
    public int getStatus() {
        return 0;
    }

    /**
     *
     */
    @Override
    public boolean isRealmAvailable() {
        return serpent2[serpent2.length - 1].isAlive();

    }
    /**
     * Retrieves the collectible rewards available in the Blue Realm.
     *
     * @return An array of Collectibles objects representing the available rewards.
     */
    public Collectibles[] getCollectibles(){
        return rewardProperties;
    }
    /**
     * Calculates the total score for the realm.
     *
     * @return The total score for the realm.
     */
    @Override
    public int getTotalScore(){
        int score=0;
        for(int i=0; i<hitcount;i++){
            score=score+scoreProperties[i];
        }
        return score;
    }
    /**
     * Calculates the score for the score sheet.
     * The score is calculated by adding the scoreProperties for each round and storing it in the scoreSheetValues array.
     */
    private void getScoreScoreSheet(){
        int score=0;
        for(int i=0; i<S1HeadNumber+S2HeadNumber;i++){
            score=scoreProperties[i]+score;
            scoreSheetValues[i]=score;
        }

    }
    /**
     * Retrieves a creature from the BlueRealm based on the rolled Dice value.
     *
     * @param dice the rolled Dice value
     * @return the retrieved Creature
     */
    @Override
    public Creature getCreature(Dice dice) {
        return serpent1[4];
    }

    /**
     * Retrieves the difference in score between the current move and the previous move in the score sheet.
     *
     * @param move the Move object representing the current move
     * @return the difference in score between the current move and the previous move in the score sheet
     */
    @Override
    public int getFakeScore(Move move) {
        int prev=(hitcount==0)?0:scoreSheetValues[hitcount-1];
        return scoreSheetValues[hitcount]-prev;
    }

    /**
     * Returns the number of elemental crests for the realm.
     *
     * @return The number of elemental crests for the realm.
     */
    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns a string representation of the BlueRealm object.
     * The string contains information about the Hydra Serpents in the Blue Realm.
     * </p>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Tide Abyss: Hydra Serpents (BLUE REALM):\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  #  |H11  |H12  |H13  |H14  |H15  |H21  |H22  |H23  |H24  |H25  |H26  |\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  H  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |%s  |\n" +
                        "|  C  |≥1   |≥2   |≥3   |≥4   |≥5   |≥1   |≥2   |≥3   |≥4   |≥5   |≥6   |\n" +
                        "|  R  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  S  |%s    |%s    |%s    |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------------------------------------------+\n\n\n",
                attackValues[0],attackValues[1],attackValues[2],attackValues[3],attackValues[4],
                attackValues[5],attackValues[6],attackValues[7],attackValues[8],attackValues[9],attackValues[10],
                rewardValues[0],rewardValues[1],rewardValues[2],rewardValues[3],rewardValues[4],
                rewardValues[5],rewardValues[6],rewardValues[7],rewardValues[8],rewardValues[9],rewardValues[10],
                scoreSheetValues[0],scoreSheetValues[1],scoreSheetValues[2],scoreSheetValues[3],scoreSheetValues[4],scoreSheetValues[5],
                scoreSheetValues[6],scoreSheetValues[7],scoreSheetValues[8],scoreSheetValues[9],scoreSheetValues[10]);
    }

    /**
     * Retrieves the values of the score sheet.
     *
     * @return an array containing the values of the score sheet.
     */
    public int[] getScoreSheetValues() {
        return scoreSheetValues;
    }

    /**
     * Retrieves the attack values for the realm.
     *
     * @return an array of String representing the attack values for the realm.
     */
    public String[] getAttackValues() {
        return attackValues;
    }

    /**
     * Retrieves the reward values of the realm.
     *
     * @return An array of Strings representing the reward values of the realm.
     */
    public String[] getRewardValues() {
        return rewardValues;
    }
    /**
     * Returns the value of the hit count for the BlueRealm instance.
     *
     * @return The value of the hit count
     */
    public int getHitcount(){
        return hitcount;
    }

    /**
     * Retrieves the reward of the realm.
     *
     * @return an array of Collectibles representing the reward of the realm
     * @Override
     */
    @Override //just returns an array of 1 element as the game controller gets an array from each realm
    public Collectibles[] getReward() {
        Collectibles[] temp=new Collectibles[1];
        temp[0]=rewardProperties[hitcount-1];
        return temp;
    }
    /**
     * Checks whether there is a reward or not after attacking.
     *
     * @return true if there is a reward, false otherwise
     */
    @Override
    public boolean checkReward() {
        if(rewardProperties[hitcount-1]!=null){
            if(rewardProperties[hitcount-1] instanceof ElementalCrest){
                noElementalCrests++;
            }
            rewardValues[hitcount-1]="X ";
            return true;
        }else{
            return false;
        }
    }


    /**
     * Attacks the serpents using the given move. Can change to use the next possible move method if needed.
     *
     * @param move the move to use for the attack
     * @return true if the attack was successful and a serpents was defeated, false otherwise
     */
    @Override
    public boolean attack(Move move) {
        //Can change this to use next possibilemove method//
        if(isSerpent1Alive()){
            for(int i=0;i<S1HeadNumber;i++){
                if ((move.getDice().getValue()>=serpent1[i].getHeadNumber())&&(serpent1[i].isAlive())){
                    serpent1[i].setStatus(false);
                    attackValues[hitcount]=move.getDice().getValue()+"  ";
                    hitcount++;
                    return true;
                }
            }
        }else{
            for(int j=0;j<S2HeadNumber;j++){
                if ((move.getDice().getValue()>=serpent2[j].getHeadNumber())&&(serpent2[j].isAlive())){
                    serpent2[j].setStatus(false);
                    attackValues[hitcount]=move.getDice().getValue()+"  ";
                    hitcount++;
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Retrieves the score properties from the TideAbyssScores.properties file.
     * If the file is not found or there is an error reading the file,
     * the method sets default values for the score properties.
     *
     * @return an array of 11 integers containing the score properties
     */
    private int[] getScoreProperties() {
        Properties properties = new Properties();
        scoreProperties=new int[11] ;
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/config/TideAbyssScores.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println("File Not Found");
            scoreProperties[0]=1;
            scoreProperties[1]=2;
            scoreProperties[2]=3;
            scoreProperties[3]=4;
            scoreProperties[4]=5;
            scoreProperties[5]=6;
            scoreProperties[6]=7;
            scoreProperties[7]=8;
            scoreProperties[8]=9;
            scoreProperties[9]=10;
            scoreProperties[10]=11;
        }
        for (int i = 0; i < 11; i++) {
            String score= properties.getProperty("head"+(i+1));
            if(score!=null){
                scoreProperties[i] = Integer.parseInt(score);
            }
            else{
                scoreProperties[i] = i+1;
                // the default condition to be done if the file has a mistake(empty)
            }
        }
        return scoreProperties;
    }

    /**
     * Retrieves the properties of rewards from the "TideAbyssRewards.properties" file.
     * If the file is not found, returns a default set of reward properties.
     *
     * @return an array of Collectibles representing the rewards properties
     */
    public Collectibles[] getRewardsProperties() {
        Properties properties = new Properties();
        Collectibles []rewardProperties=new Collectibles[11] ;
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/config/TideAbyssRewards.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println("File Not Found");
            rewardProperties[0]=null;
            rewardProperties[1]=null;
            rewardProperties[2]=null;
            rewardProperties[3]=null;
            rewardProperties[4]=new ArcaneBoost();
            rewardProperties[5]=null;
            rewardProperties[6]=new ColorBonus(GameColor.GREEN);
            rewardProperties[7]=new ElementalCrest();
            rewardProperties[8]=null;
            rewardProperties[9]=new ColorBonus(GameColor.MAGENTA);
            rewardProperties[10]=new TimeWarp();
        }
        for (int i = 0; i < 11; i++) {
            String reward = properties.getProperty("hit"+(i+1)+"Reward");
            if(reward!="null"){
                switch (reward){
                    case "TimeWarp":{
                        rewardProperties[i]=new TimeWarp();
                        rewardValues[i]="TW";
                        break;
                    }
                    case "ArcaneBoost":{
                        rewardProperties[i]=new ArcaneBoost();
                        rewardValues[i]="AB";
                        break;
                    }
                    case "EssenceBonus":{
                        rewardProperties[i]=new EssenceBonus();
                        rewardValues[i]="EC";
                        break;
                    }
                    case "RedBonus":{
                        rewardProperties[i]=new ColorBonus(GameColor.RED);
                        rewardValues[i]="RB";
                        break;
                    }
                    case "BlueBonus":{
                        rewardProperties[i]=new ColorBonus(GameColor.BLUE);
                        rewardValues[i]="BB";
                        break;
                    }
                    case "GreenBonus":{
                        rewardProperties[i]=new ColorBonus(GameColor.GREEN);
                        rewardValues[i]="GB";
                        break;
                    }
                    case "MagentaBonus":{
                        rewardProperties[i]=new ColorBonus(GameColor.MAGENTA);
                        rewardValues[i]="MB";
                        break;
                    }
                    case "YellowBonus":{
                        rewardProperties[i]=new ColorBonus(GameColor.YELLOW);
                        rewardValues[i]="YB";
                        break;
                    }
                    case "ElementalCrest":{
                        rewardProperties[i]=new ElementalCrest();
                        rewardValues[i]="EC";
                        break;
                    }
                    default:{
                        rewardProperties[i]=null;
                        rewardValues[i]="  ";
                    }
                }
            }
        }
        return rewardProperties;
    }
    /**
     * Returns an array of possible moves in the current realm.
     *
     * @return an array of possible moves in the current realm
     */
    @Override
    public Move[] getRealmMoves() {
        LinkedList<Move> list =new LinkedList<>();
        if(isSerpent1Alive()){
            for(int i=0;serpent1[i].getHeadNumber()<=5;i++){
                if (serpent1[i].isAlive()){
                    for(int j=i+1;j<=6;j++){
                        Move mover=new Move(new BlueDice(j),new Serpent());
                        list.addLast(mover);
                    }
                    break;
                }
            }
        }else{
            for(int i=0;serpent2[i].getHeadNumber()-S1HeadNumber<6;i++){
                if ((serpent2[i].isAlive())){
                    for(int j=i+1;j<=6;j++){
                        Move mover=new Move(new BlueDice(j),new Serpent());
                        list.addLast(mover);
                    }
                    break;
                }
            }
        }

        return list.toArray(Move[]::new);
    }

    /**
     * Checks whether the serpent 1 is alive.
     *
     * @return {@code true} if serpent 1 is alive, {@code false} otherwise.
     */
    public boolean isSerpent1Alive(){
        return serpent1[S1HeadNumber-1].isAlive();
    }

}