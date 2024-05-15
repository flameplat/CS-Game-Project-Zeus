package game.realms;

import game.utilities.Color;
import game.collectibles.*;
import game.creatures.Creature;
import game.creatures.Guardian;
import game.engine.*;
import game.dice.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class GreenRealm extends Realm{
    // -----------------------Attributes-----------------------//
    private static final Color realmColor=Color.GREEN;
    private static final String name="Terras Heartland";
    private Move[] realmMoves;
    private Move[] availableRealmMoves;
    private Collectibles[] collectibles;
    private String[] attackValues;
    private String[] rewardValues;
    private Guardian guardian;
    private int realmScore;
    private int deadGuardians;
    private int noElementalCrests;
    



    // -----------------------Constructor-----------------------//
    public GreenRealm(){
        this.deadGuardians = 0;
        this.guardian=new Guardian();
        this.realmScore = 0;
        this.noElementalCrests = 0;
        this.attackValues = new String[]{"X","2","3","4","5","6","7","8","9","10","11","12"};
        this.rewardValues = new String[7];
        this.realmMoves = new Move[11];
        for(int i=2;i<13;i++){
            realmMoves[i-2] = new Move(new GreenDice(i),new Guardian());
        }
        this.availableRealmMoves = realmMoves;
        this.collectibles = getRewardsProperties();


    }
    // -----------------------Methods-----------------------//
    public Move[] getAvialableRealmMoves(){
        return availableRealmMoves;
    }
    public Collectibles[] getRewardsProperties(){
        Properties properties = new Properties();
        Collectibles []rewardProperties=new Collectibles[7] ;
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/config/TerrasHeartlandRewards.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException e){
            System.out.println("File Not Found");
        }
        for (int i = 0; i <3; i++) {
            String rowReward = properties.getProperty("row"+(i+1)+"Reward");
            rewardProperties[i]=Collectibles.getCollectibleFromString(rowReward);
            if(rewardProperties[i]==null){
                System.out.println("Error in reading the file");
                System.exit(1);
            }
            rewardValues[i] = rewardProperties[i].toString();
        }
        for (int i=3;i<7;i++){
            String columnReward = properties.getProperty("column"+(i-2)+"Reward");
            rewardProperties[i] = Collectibles.getCollectibleFromString(columnReward);
            if(rewardProperties[i]==null){
                System.out.println("Error in reading the file");
                System.exit(1);
            }
            rewardValues[i] = rewardProperties[i].toString();
        } 
        return rewardProperties;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return realmColor;
    }


    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public boolean isRealmAvailable() {
        return deadGuardians < 11;
    }

    @Override
    public Collectibles[] getReward() {
        Collectibles[] earnedRewards = new Collectibles[2];
        if(checkRowReward()){
            earnedRewards[0] = getRowReward();
        }
        if(checkColumnReward()){
            earnedRewards[1] = getColumnReward();
        }
        return earnedRewards;
        
    }
    public Collectibles getRowReward(){
        Collectibles[] tmp = new Collectibles[1];
        //checks rewards in rows(c count position of column, l loop position of row)
        for(int c=0,l=0;l<3;c++){
            if(!attackValues[c + l * 4].equals("X")){
                l++;c=0;
            }
            if(c == 3){
                if(!rewardValues[l].equals("X")){
                    tmp[0] = collectibles[l];
                    rewardValues[l] = "X";
                    return tmp[0];
                }
                else{
                    c=0;l++;
                }

            }
        }
        return null;
    }
    public Collectibles getColumnReward(){
        Collectibles[] tmp = new Collectibles[1];
        //checks rewards in columns(r count position of row, l loop position of column)
        for(int r=0, l=0;l<4;r++){
            if(!attackValues[r * 4 + l].equals("X")){
                l++;r=0;
            }
            if(r == 2){
                if(!rewardValues[l + 3].equals("X")){
                    tmp[0] = collectibles[l+3];
                    rewardValues[l+3] = "X";
                    return tmp[0];
                }
                else{
                    r=0;l++;
                }
            }
        }
        return null;
    }
    @Override
    public boolean checkReward() {
        return checkRowReward() || checkColumnReward();
    }
    public boolean checkRowReward(){
        //checks rewards in rows(c count position of column, l loop position of row)
        for(int c=0,l=0;l<3;c++){
            if(!attackValues[c + l * 4].equals("X")){
                l++;c=0;
            }
            if(c == 3){
                if(!rewardValues[l].equals("X")){
                    if(collectibles[l]  instanceof ElementalCrest){
                        noElementalCrests++;
                    }
                    return true;
                }
                else{
                    c=0;l++;
                }

            }
        }
        return false;
    }

    public boolean checkColumnReward(){
        //checks rewards in columns(r count position of row, l loop position of column)
        for(int r=0, l=0;l<4;r++){
            if(!attackValues[r * 4 + l].equals("X")){
                l++;r=0;
            }
            if(r == 2){
                if(!rewardValues[l + 3].equals("X")){
                    if(collectibles[l+3]  instanceof ElementalCrest){
                        noElementalCrests++;
                    }
                    return true;
                }
                else{
                    r=0;l++;
                }
            }
        }
        return false;
    }

    @Override
    public boolean attack(Move move) {
        if(isRealmAvailable()){
            if(move.getDice().getRealm()== Color.GREEN || move.getDice().getRealm()== Color.WHITE){
                int sumOfValues = move.getDice().getValue();
                for(Move availableRealmMove : availableRealmMoves){
                    if(sumOfValues == availableRealmMove.getDice().getValue()){
                       availableRealmMove.getCreature().attack();
                       deadGuardians++;
                       if(deadGuardians>= 2)
                           realmScore += deadGuardians - 1;

                       else{
                            realmScore = 1;}
                       updateAvailableMoves(move);
                       return true;
                    }
                }
                return false;
            }

            }
            return false;
        }

    public void updateAvailableMoves(Move move){
        Move[] tmp = new Move[availableRealmMoves.length-1];
        for(int i=0,k=0;i<availableRealmMoves.length;i++){
            if(availableRealmMoves[i] != move){
                tmp[k] = availableRealmMoves[i];
                k++;
            }
            if(availableRealmMoves[i] == move){
                for(int j=0;j<attackValues.length;j++){
                    if(!attackValues[j].equals("X") && parseInt(attackValues[j]) == availableRealmMoves[i].getDice().getValue()){
                        attackValues[j] = "X";
                    }
                }
            }
        }
        availableRealmMoves = tmp;
    }

    @Override
    public int getTotalScore() {
        return realmScore;
    }

    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    @Override
    public String toString() {
        String string = String.format(
                "Terra's Heartland: Gaia Guardians (GREEN REALM):\n" +
                        "+-----------------------------------+\n" +
                        "|  #  |1    |2    |3    |4    |R    |\n" +
                        "+-----------------------------------+\n" +
                        "|  1  |%s    |%s    |%s    |%s    |%s   |\n" +
                        "|  2  |%s    |%s    |%s    |%s    |%s   |\n" +
                        "|  3  |%s    |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------+\n" +
                        "|  R  |%s   |%s   |%s   |%s   |     |\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  S  |1    |2    |4    |7    |11   |16   |22   |29   |37   |46   |56   |\n" +
                        "+-----------------------------------------------------------------------+\n\n" +
                        "\n"
                ,attackValues[0],attackValues[1],attackValues[2],attackValues[3],rewardValues[0],
                attackValues[4],attackValues[5],attackValues[6],attackValues[7],rewardValues[1],
                attackValues[8],attackValues[9],attackValues[10],attackValues[11],rewardValues[2],
                rewardValues[3],rewardValues[4],rewardValues[5],rewardValues[6]);
        return string;
    }

    @Override
    public Move[] getRealmMoves() {
        return realmMoves;
    }

    @Override
    public Creature getCreature(Dice dice) {
        if(dice.getRealm()==Color.GREEN){
            return guardian;
        }
        else
            return null;

    }
}

