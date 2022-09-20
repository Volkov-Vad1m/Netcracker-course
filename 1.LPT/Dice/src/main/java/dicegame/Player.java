package dicegame;
import java.util.Random;

public class Player {
    private static int amountOfPlayers;

    private int id;
    private int tossResult;
    private int score;

    private boolean isReal = true;

    public Player() {
        amountOfPlayers++;
        id = amountOfPlayers;
    }

    public int getId() {
        return id;
    }

    public int getTossResult() {
        return tossResult;
    }

    public void tossesCubes(int cubes) {
        int result = 0;
        Random random = new Random();

        for(int i = 0; i < cubes; i++) {
            result = result + random.nextInt(6) + 1;
        }

        this.tossResult = result;
    }

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
}
