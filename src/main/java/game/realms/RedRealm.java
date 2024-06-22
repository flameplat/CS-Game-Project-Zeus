package game.realms;

import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;
import game.creatures.Creature;
import game.creatures.Dragon;
import game.creatures.HitRegionsOfDragons;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.Move;
import game.utilities.GameColor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class RedRealm extends Realm {
    private static final GameColor REALM_GAME_COLOR = GameColor.RED;
    private static final String name = "\u001B[31m" + "Red Realm" + "\u001B[0m";
    private final LinkedList<Move> redMoves;
    // -----------------------Attributes-----------------------//
    private int totalRealmScore;
    private Dragon[] dragons;
    private Object[] collectibles;
    private int noElementalCrests;
    private LinkedList<Collectibles> realmRewards;
    private int[] dragonsScore;

    /**
     * Represents a Red Realm in the game.
     * Inherits from the Realm class and contains methods and properties specific to the Red Realm.
     */
    // -----------------------Constructor-----------------------//
    public RedRealm() {
        this.dragonsScore = new int[4];
        this.totalRealmScore = 0;
        this.noElementalCrests = 0;
        this.collectibles = getRewardsProperties();
        this.dragons = initDragons();
        this.redMoves = redMovespopulate();
    }

    /**
     * Populates the linked list of red moves.
     *
     * @return The populated linked list of red moves.
     */
    // -----------------------Methods-----------------------//
    private LinkedList<Move> redMovespopulate() {
        LinkedList<Move> temp = new LinkedList<>();
        for (int i = 1; i < 7; i++) {
            int dragonhit = 0;
            int dragonhit_1 = 0;
            switch (i) {
                case 1:
                case 3:
                    dragonhit = 1;
                    dragonhit_1 = 2;
                    break;
                case 2:
                    dragonhit = 1;
                    dragonhit_1 = 3;
                    break;
                case 4:
                case 5:
                    dragonhit = 3;
                    dragonhit_1 = 4;
                    break;
                case 6:
                    dragonhit = 2;
                    dragonhit_1 = 4;
                    break;
            }
            temp.add(new Move(new RedDice(i), dragons[dragonhit - 1]));
            temp.add(new Move(new RedDice(i), dragons[dragonhit_1 - 1]));
        }
        return temp;
    }

    /**
     * Initializes an array of Dragon objects with specific health attributes and scores.
     *
     * @return an array of Dragon objects
     */
    // ENTER VALUES FOR:FACE,WINGS,TAIL,HEART
    // NA->0
    private Dragon[] initDragons() {
        dragons = new Dragon[4];
        dragons[0] = new Dragon(new Object[]{3, 2, 1, "X"}, dragonsScore[0], 1);
        dragons[1] = new Dragon(new Object[]{6, 1, "X", 3}, dragonsScore[1], 2);
        dragons[2] = new Dragon(new Object[]{5, "X", 2, 4}, dragonsScore[2], 3);
        dragons[3] = new Dragon(new Object[]{"X", 5, 4, 6}, dragonsScore[3], 4);
        return dragons;
    }

    /**
     * Retrieves the rewards properties from the configuration files.
     *
     * @return an array containing the reward properties
     */
    private Object[] getRewardsProperties() {
        Properties properties = new Properties();
        Properties dragonScoreProperties = new Properties();
        Object[] rewardProperties = new Object[5];
        try {
            FileInputStream dragonScoreFileInputStream = new FileInputStream("src/main/resources/config/EmberfallDominionScores.properties");
            FileInputStream fileInputStream = new FileInputStream(
                    "src/main/resources/config/EmberfallDominionRewards.properties");
            properties.load(fileInputStream);
            dragonScoreProperties.load(dragonScoreFileInputStream);
            dragonScoreFileInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        try {
            for (int i = 0; i < dragonsScore.length; i++) {
                dragonsScore[i] = Integer.parseInt(dragonScoreProperties.getProperty("dragon" + (i + 1)));
            }
        } catch (NumberFormatException e) {
            dragonsScore = new int[]{10, 14, 16, 20};
        }

        for (int i = 0; i < 5; i++) {
            String diagonal_reward = properties.getProperty("diagonalReward");
            if (diagonal_reward != null) {
                rewardProperties[i] = Collectibles.getCollectibleFromString(diagonal_reward);
            }

            String reward = properties.getProperty("row" + (i + 1) + "Reward");
            if (reward != null) {
                rewardProperties[i] = Collectibles.getCollectibleFromString(reward);
            }
        }
        return rewardProperties;
    }

    /**
     * Attacks the opponent's creature with the given move.
     *
     * @param move The move containing the creature and dice used for the attack.
     * @return true if the attack is successful, false otherwise.
     */
    // Gets from Move: Creature and dice
    public boolean attack(Move move) {
        if (isRealmAvailable()) {
            Dragon dragon = new Dragon();
            if (redMoves.contains(move)) {
                for (Move m : redMoves) {
                    if (m.equals(move)) {
                        dragon = (Dragon) m.getCreature();
                    }
                }
                move.execute();
                redMoves.remove(move);
                for (int i = 0; i < dragon.getHealth().length; i++) {
                    if (!dragon.getHealth()[i].equals("X")
                            && (int) dragon.getHealth()[i] == move.getDice().getValue()) {
                        switch (i) {
                            case 0:
                                dragon.attack(move.getDice().getValue(), HitRegionsOfDragons.FACE);
                                break;

                            case 1:
                                dragon.attack(move.getDice().getValue(), HitRegionsOfDragons.WING);
                                break;

                            case 2:
                                dragon.attack(move.getDice().getValue(), HitRegionsOfDragons.TAIL);
                                break;
                            case 3:
                                dragon.attack(move.getDice().getValue(), HitRegionsOfDragons.HEART);
                                break;
                        }
                    }
                }

            }
            return true;
        }
        return false;
    }

    /**
     * Returns the name of the realm.
     * @return The name of the realm as a String.
     */
    // get the name of the realm
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the color of the realm.
     *
     * @return the color of the realm
     */
    // get the realm color
    @Override
    public GameColor getColor() {
        return REALM_GAME_COLOR;
    }

    /**
     * Checks if there are any alive dragons in the realm.
     *
     * @return true if there is at least one alive dragon, false otherwise.
     */
    // if possible moves array length ==0 there
    @Override
    public boolean isRealmAvailable() {
        boolean flage = false;
        for (Dragon dragon : dragons) {
            if (dragon.isAlive())
                flage = true;
        }
        return flage;
    }

    /**
     * Removes a collectible from the collectibles array at the specified index.
     * Replaces the collectible at the specified index with "X ".
     *
     * @param k the index of the collectible to be removed
     * @return an array containing the updated collectibles
     */
    private Object[] removeCollectible(int k) {
        LinkedList<Object> newCollectibles = new LinkedList<>();
        for (int i = 0; i < collectibles.length; i++) {
            if (i == k)
                newCollectibles.add("X ");
            else
                newCollectibles.add(collectibles[i]);
        }
        return newCollectibles.toArray();
    }

    /**
     * Checks if there is a reward available in the RedRealm.
     *
     * @return true if a reward is available, otherwise false
     */
    @Override
    public boolean checkReward() {
        this.realmRewards = new LinkedList<>();
        String getReward = "XXXX";
        boolean flage = false;
        StringBuilder checkDiagonal = new StringBuilder();
        for (int i = 0; i < dragons.length; i++) {
            StringBuilder checkHorizontal = new StringBuilder();
            for (int j = 0; j <= dragons[i].getHealth().length; j++) {
                if (j == 4 && !checkHorizontal.toString().equals(getReward))
                    break;
                if (j == 4) {
                    if (collectibles[i] instanceof Collectibles) {
                        if (collectibles[i] instanceof ElementalCrest) {
                            noElementalCrests++;
                        }
                        realmRewards.add((Collectibles) collectibles[i]);
                        collectibles = removeCollectible(i);
                        flage = true;
                    }
                } else
                    checkHorizontal.append(dragons[j].getHealth()[i]);
            }

        }
        for (int i = 0; i <= dragons.length; i++) {
            if (i == 4 && !checkDiagonal.toString().equals(getReward))
                break;
            if (i == 4) {
                if (collectibles[i] instanceof Collectibles) {
                    realmRewards.add((Collectibles) collectibles[i]);
                    collectibles = removeCollectible(i);
                    flage = true;
                }
            } else {
                checkDiagonal.append(dragons[i].getHealth()[i]);
            }
        }
        return flage;
    }

    /**
     * Retrieves the rewards obtained in the realm.
     *
     * @return An array of Collectibles representing the rewards obtained in the realm.
     */
    @Override
    public Collectibles[] getReward() {

        return realmRewards.toArray(Collectibles[]::new);

    }

    /**
     * Calculates the total score of the RedRealm.
     * This method iterates through each Dragon in the RedRealm's list of dragons and checks if the health of the Dragon
     * matches a specific pattern stored in the variable get_score. If the health matches, the score of the Dragon is added
     * to the totalRealmScore1 variable. The totalRealmScore1 is then assigned to the totalRealmScore variable. Finally,
     * the method returns the totalRealmScore.
     *
     * @return the total score of the RedRealm.
     */
    @Override
    public int getTotalScore() {
        String get_score = "XXXX";
        int totalRealmScore1 = 0;
        for (Dragon dragon : dragons) {
            StringBuilder check_region = new StringBuilder();
            for (int j = 0; j < dragon.getHealth().length; j++) {
                check_region.append(dragon.getHealth()[j]);
            }
            if (check_region.toString().equals(get_score)) {
                totalRealmScore1 += dragon.getScore();
            }
        }
        totalRealmScore = totalRealmScore1;
        return totalRealmScore;
    }

    /**
     *
     * This method returns the number of elemental crests associated with the realm.
     *
     * @return The number of elemental crests.
     */
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    /**
     * Returns a formatted string representation of the RedRealm object.
     *
     * The returned string contains the information about the Pyroclast Dragon in the Red Realm,
     * including the dragon's health, collectibles, and dragons score.
     *
     * @return a formatted string representation of the RedRealm object
     */
    @Override
    public String toString() {

        return String.format("Emberfall Dominion: Pyroclast Dragon (RED REALM):\n" +
                        "+-----------------------------------+\n" +
                        "|  #  |D1   |D2   |D3   |D4   |R    |\n" +
                        "+-----------------------------------+\n" +
                        "|  F  |%s    |%s    |%s    |X    |%s   |\n" +
                        "|  W  |%s    |%s    |X    |%s    |%s   |\n" +
                        "|  T  |%s    |X    |%s    |%s    |%s   |\n" +
                        "|  H  |X    |%s    |%s    |%s    |%s   |\n" +
                        "+-----------------------------------+\n" +
                        "|  S  |%s   |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------+\n\n\n", dragons[0].getHealth()[0], dragons[1].getHealth()[0],
                dragons[2].getHealth()[0], collectibles[0],
                dragons[0].getHealth()[1], dragons[1].getHealth()[1], dragons[3].getHealth()[1], collectibles[1],
                dragons[0].getHealth()[2], dragons[2].getHealth()[2], dragons[3].getHealth()[2], collectibles[2],
                dragons[1].getHealth()[3], dragons[2].getHealth()[3], dragons[3].getHealth()[3], collectibles[3],
                (dragonsScore[0] < 10) ? dragonsScore[0] + " " : dragonsScore[0], (dragonsScore[1] < 10) ? dragonsScore[1] + " " : dragonsScore[1], (dragonsScore[2] < 10) ? dragonsScore[2] + " " : dragonsScore[2], (dragonsScore[3] < 10) ? dragonsScore[3] + " " : dragonsScore[3], collectibles[4]);
    }
    /**
     * Retrieves an array of Dragon objects representing the dragons in the Red Realm.
     *
     * @return An array of Dragon objects.
     */
    public Dragon[] getDragons(){
        return dragons;
    }
    /**
     * Retrieves the collectibles from the RedRealm.
     *
     * @return An array containing the collectibles.
     */
    public Object[] getCollectibles(){
        return collectibles;
    }
    /**
     * Returns the score of the dragons in the red realm.
     *
     * @return An array containing the scores of the dragons in the red realm.
     */
    public int[] getDragonsScore(){
        return dragonsScore;
    }

    /**
     * Retrieves the available moves in the realm.
     *
     * @return an array of Move objects representing the available moves in the realm. If no moves are available, an empty array is returned.
     */
    @Override
    public Move[] getRealmMoves() {
        if (isRealmAvailable())
            return redMoves.toArray(Move[]::new);
        return new Move[0];
    }
    /**
     * Returns a LinkedList of Moves representing the possible moves in the realm.
     *
     * @return LinkedList<Move> - a LinkedList of Moves, null if the realm is not available.
     */
    public LinkedList<Move> getRealmMovesList(){
        if(isRealmAvailable()){
            return redMoves;
        }
        return null;
    }

    /**
     * Retrieves the creature based on the provided dice.
     *
     * @param dice The dice used to determine the creature to retrieve. Should be an instance of RedDice and the value of the dice must be between 1 and 6.
     * @return The creature corresponding to the dice value, or null if the conditions are not met.
     */
    @Override
    public Creature getCreature(Dice dice) {
        if (dice.getRealm() == GameColor.RED && dice instanceof RedDice && (dice.getValue() <= 6 && dice.getValue() >= 1)) {
            if (((RedDice) dice).getDragonNumber() == 0) {
                return dragons[0];
            }
            return dragons[((RedDice) dice).getDragonNumber() - 1];

        }
        return null;
    }

    /**
     * Returns the fake score for a given move.
     *
     * @param move the move to calculate the fake score for
     * @return the fake score for the given move
     */
    @Override
    public int getFakeScore(Move move) {
        return 0;
    }


    /**
     * Retrieves the status of the realm.
     *
     * @return the status of the realm
     * @see Realm
     */
    // get the realm status
    public int getStatus() {
        return 0;
    }
}