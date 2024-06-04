package game.realms;

import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;
import game.creatures.Creature;
import game.creatures.Guardian;
import game.dice.Dice;
import game.dice.GreenDice;
import game.engine.Move;
import game.utilities.GameColor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class GreenRealm extends Realm {

    private static final GameColor REALM_GAME_COLOR = GameColor.GREEN;
    // -----------------------Attributes-----------------------//
    private final Guardian[][] mainArray;
    private final Object[] rowRewards;
    private final Guardian[] gardians;
    private final Object[] colRewards;
    private final LinkedList<Move> possibleMoves;
    Collectibles[] currentRewards;
    private int[] score;
    private int count;
    private int noElementalCrests;

    // -----------------------Constructor-----------------------//
    public GreenRealm() {

        this.score = new int[11];
        this.possibleMoves = new LinkedList<>();
        this.rowRewards = new Object[3];
        this.colRewards = new Object[4];
        count = 0;
        gardians = new Guardian[11];
        for (int i = 2; i < 13; i++) {
            gardians[i - 2] = new Guardian(i);
        }
        Guardian deadGuardian = new Guardian();
        deadGuardian.attack();
        loadProperties();
        this.mainArray = new Guardian[][]{
                {deadGuardian, gardians[0], gardians[1], gardians[2]},
                {gardians[3], gardians[4], gardians[5], gardians[6]},
                {gardians[7], gardians[8], gardians[9], gardians[10]}
        };
        for (Guardian gardian : gardians) {
            possibleMoves.add(new Move(new GreenDice(gardian.getScore()), gardian));
        }
    }

    // -----------------------Methods-----------------------//
    @Override
    public String getName() {
        return "\u001B[32m" + "Green Realm" + "\u001B[0m";
    }

    @Override
    public GameColor getColor() {
        return REALM_GAME_COLOR;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public boolean isRealmAvailable() {
        return count < 12;
    }

    @Override
    public Collectibles[] getReward() {
        return currentRewards;
    }

    @Override
    public boolean checkReward() {
        LinkedList<Collectibles> rewards = new LinkedList<>();
        for (int i = 0; i < mainArray.length; i++) {
            int rowSum = 0;
            int j;
            for (j = 0; j < mainArray[i].length; j++) {
                if (!mainArray[i][j].isAlive()) {
                    rowSum += 1;
                }
            }
            if (rowSum == 4) {
                Object rowReward = rowRewards[i];
                if (rowReward != "X ") {
                    if (rowReward instanceof ElementalCrest) {
                        noElementalCrests++;
                    }
                    rewards.add((Collectibles) rowReward);
                    rowRewards[i] = "X ";
                }

            }
        }

        // Check columns
        for (int j = 0; j < mainArray[0].length; j++) {
            int columnSum = 0;
            int i;
            for (i = 0; i < mainArray.length; i++) {
                if (!mainArray[i][j].isAlive()) {
                    columnSum += 1;
                }
            }
            if (columnSum == 3) {
                Object colReward = colRewards[j];
                if (colReward != "X ") {
                    if (colReward instanceof ElementalCrest) {
                        noElementalCrests++;
                    }
                    rewards.add((Collectibles) colReward);
                    colRewards[j] = "X ";
                }

            }
        }
        this.currentRewards = rewards.toArray(Collectibles[]::new);
        return currentRewards.length != 0;
    }

    @Override
    public boolean attack(Move move) {
        if (isRealmAvailable()) {
            if (possibleMoves.contains(move)) {
                possibleMoves.remove(move);
                for (Guardian[] guardians : mainArray) {
                    for (Guardian guardian : guardians) {
                        if (guardian.getScore() == move.getDice().getValue()) {
                            guardian.attack();
                            count++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getTotalScore() {
        if (count == 0) {
            return 0;
        }
        return score[count - 1];
    }

    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    @Override
    public String toString() {
        return String.format("Terra's Heartland: Gaia Guardians (GREEN REALM):\n" +
                        "+-----------------------------------+\n" +
                        "|  #  |1    |2    |3    |4    |R    |\n" +
                        "+-----------------------------------+\n" +
                        "|  1  |%s    |%s    |%s    |%s    |%s   |\n" +
                        "|  2  |%s    |%s    |%s    |%s    |%s   |\n" +
                        "|  3  |%s    |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------+\n" +
                        "|  R  |%s   |%s   |%s   |%s   |     |\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  S  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------------------------------------------+\n\n\n",
                mainArray[0][0], mainArray[0][1], mainArray[0][2], mainArray[0][3], rowRewards[0],
                mainArray[1][0], mainArray[1][1], mainArray[1][2], mainArray[1][3], rowRewards[1],
                mainArray[2][0], (mainArray[2][1].toString().length() == 1) ? mainArray[2][1] + " " : mainArray[2][1], (mainArray[2][2].toString().length() == 1) ? mainArray[2][2] + " " : mainArray[2][2], (mainArray[2][3].toString().length() == 1) ? mainArray[2][3] + " " : mainArray[2][3], rowRewards[2],
                colRewards[0], colRewards[1], colRewards[2], colRewards[3],
                score[0] < 10 ? score[0] + " " : score[0], score[1] < 10 ? score[1] + " " : score[1], score[2] < 10 ? score[2] + " " : score[2], score[3] < 10 ? score[3] + " " : score[3], score[4] < 10 ? score[4] + " " : score[4], score[5] < 10 ? score[5] + " " : score[5], score[6] < 10 ? score[6] + " " : score[6], score[7] < 10 ? score[7] + " " : score[7], score[8] < 10 ? score[8] + " " : score[8], score[9] < 10 ? score[9] + " " : score[9], score[10] < 10 ? score[10] + " " : score[10]
        );
    }
    public Guardian[][] getMainArray(){
        return mainArray;
    }
    public Object[] getColRewards(){
        return colRewards;
    }
    public int[] getScore(){
        return score;
    }
    public Object[] getRowRewards(){
        return rowRewards;
    }


    @Override
    public Move[] getRealmMoves() {
        return possibleMoves.toArray(Move[]::new);
    }

    @Override
    public Creature getCreature(Dice dice) {
        for (Guardian[] guardians : mainArray) {
            for (Guardian guardian : guardians) {
                if (guardian.getScore() == dice.getValue()) {
                    return guardian;
                }
            }
        }
        return null;
    }


    public LinkedList<Guardian> getAliveCreatures() {
        LinkedList<Guardian> aliveGardians = new LinkedList<>();
        for (Guardian g : gardians) {
            if (g.isAlive()) {
                aliveGardians.add(g);
            }
        }
        return aliveGardians;
    }

    public Guardian[] getAllCreatures() {
        return gardians;
    }

    private void loadProperties() {
        Properties properties = new Properties();
        Properties gaiaScoreProperties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/TerrasHeartlandRewards.properties");
            FileInputStream gaiaScoreStream = new FileInputStream("src/main/resources/config/TerrasHeartlandScores.properties");
            properties.load(fileInputStream);
            gaiaScoreProperties.load(gaiaScoreStream);
            fileInputStream.close();
            gaiaScoreStream.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        //Row rewards
        for (int i = 0; i < 3; i++) {
            String reward = properties.getProperty("row" + (i + 1) + "Reward");
            Collectibles collectible = Collectibles.getCollectibleFromString(reward);
            rowRewards[i] = (collectible == null) ? "X " : collectible;
        }
        for (int i = 0; i < 4; i++) {
            String reward = properties.getProperty("column" + (i + 1) + "Reward");
            Collectibles collectible = Collectibles.getCollectibleFromString(reward);
            colRewards[i] = (collectible == null) ? "X " : collectible;
        }
        try {
            for (int i = 0; i < score.length; i++) {
                score[i] = Integer.parseInt(gaiaScoreProperties.getProperty("attack" + (i + 1)));
            }
        } catch (NumberFormatException e) {
            score = new int[]{1, 2, 4, 7, 11, 16, 22, 29, 37, 46, 56};
        }

    }
}
