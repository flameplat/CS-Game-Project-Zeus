package game.exceptions;

public class NegativeScoreException extends CheatDetectedException {
    public NegativeScoreException() {
    }

    @Override
    public String getMessage() {
        return "Negative score detected!";
    }
}
