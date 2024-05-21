package game.realms;

import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;
import game.creatures.Creature;
import game.creatures.Dragon;
import game.creatures.HitRegionsOfDragons;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.Move;
import game.utilities.Color;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class RedRealm extends Realm {
    private static final Color realmColor = Color.RED;
    private static final String name = "\u001B[31m" + "Red Realm" + "\u001B[0m";
    private final LinkedList<Move> redMoves;
    // -----------------------Attributes-----------------------//
    private int totalRealmScore;
    private Dragon[] dragons;
    private Object[] collectibles;
    private int noElementalCrests;
    private LinkedList<Collectibles> realmRewards;
    private int[] dragonsScore;

    // -----------------------Constructor-----------------------//
    public RedRealm() {
        this.dragonsScore = new int[4];
        this.totalRealmScore = 0;
        this.noElementalCrests = 0;
        this.collectibles = getRewardsProperties();
        this.dragons = initDragons();
        this.redMoves = redMovespopulate();
    }

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

    // get the name of the realm
    @Override
    public String getName() {
        return name;
    }

    // get the realm color
    @Override
    public Color getColor() {
        return realmColor;
    }

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

    @Override
    public Collectibles[] getReward() {

        return realmRewards.toArray(Collectibles[]::new);

    }

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

    public int getNoElementalCrests() {
        return noElementalCrests;
    }

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

    @Override
    public Move[] getRealmMoves() {
        if (isRealmAvailable())
            return redMoves.toArray(Move[]::new);
        return new Move[0];
    }

    @Override
    public Creature getCreature(Dice dice) {
        if (dice.getRealm() == Color.RED && dice instanceof RedDice && (dice.getValue() <= 6 && dice.getValue() >= 1)) {
            if (((RedDice) dice).getDragonNumber() == 0) {
                return dragons[0];
            }
            return dragons[((RedDice) dice).getDragonNumber() - 1];

        }
        return null;
    }

    // get the realm status
    public int getStatus() {
        return 0;
    }
}