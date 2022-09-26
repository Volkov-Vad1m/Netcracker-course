package wolf.player;

public interface Player {

    int getId();

    /**
     * The result of points in the round.
     * For example: Player 1 has 21 on the dice.
     */
    int getTurnResult();

    void setTurnResult(int turnResult);

    /**
     * final score of the game
     * For example: Player 1 has 3 wins in the 7 rounds.
     */
    int getScore();

    void setScore(int score);
}
