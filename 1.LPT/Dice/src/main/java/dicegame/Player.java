package dicegame;
import java.util.Random;

public class Player {
    /** static variable which counts amount of players */
    private static int amountOfPlayers;

    private int id;

    /** result of tossing cubes in a particular round */
    private int tossResult;

    private int score;

    /** if players is PC - false*/
    private boolean isReal = true;

    // ************************************************************************

    public Player() {
        amountOfPlayers++;
        id = amountOfPlayers;
    }

    // ************************************************************************
    // getters and setters
    // ************************************************************************

    public int getId() {
        return id;
    }

    public int getTossResult() {
        return tossResult;
    }

    // ************************************************************************
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isReal() {
        return isReal;
    }

    public void setReal(boolean real) {
        isReal = real;
    }

    public void tossesCubes(int cubes) {
        int result = 0;
        Random random = new Random();

        for (int i = 0; i < cubes; i++) {
            result = result + random.nextInt(6) + 1;
        }

        this.tossResult = result;
    }
}
