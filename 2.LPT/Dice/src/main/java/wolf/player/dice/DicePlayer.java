package wolf.player.dice;

import wolf.player.Player;

public class DicePlayer implements Player {

    private int id;

    /**
     * result of tossing cubes in a particular round
     */
    private int turnResult;

    private int score;

    // ************************************************************************

    public DicePlayer(int id) {
        this.id = id;
    }

    // ************************************************************************
    // getters and setters
    // ************************************************************************

    public int getId() {
        return id;
    }

    public int getTurnResult() {
        return turnResult;
    }

    public void setTurnResult(int turnResult) {
        this.turnResult = turnResult;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // ************************************************************************
    @Override
    public String toString() {
        return "Player " + id;
    }
}
