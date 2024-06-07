package game.engine;

import game.creatures.Dragon;

public class Decision {
    private boolean isMade;
    private int rowContribution;
    private Move move;
    private int colContribution;
    public Decision(Move move){
        rowContribution=0;
        colContribution=0;
        this.move=move;

    }

    public boolean isMade() {
    }

    public void makeDecision() {
        isMade = true;
    }

    public int getRowContribution() {
        return rowContribution;
    }

    public void setRowContribution(int rowContribution) {
        this.rowContribution = rowContribution;
    }

    public int getColContribution() {
        return colContribution;
    }

    public void setColContribution(int colContribution) {
        this.colContribution = colContribution;
    }
}
