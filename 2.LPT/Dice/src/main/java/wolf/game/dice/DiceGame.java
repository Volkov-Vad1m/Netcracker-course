package wolf.game.dice;

import wolf.game.Game;
import wolf.player.dice.ComputerPlayer;
import wolf.player.dice.DicePlayer;
import wolf.player.Player;

import java.util.Random;

public class DiceGame implements Game {

    public class Dice {

        public int roll() {
            Random random = new Random();
            int result = 0;

            for (int i = 0; i < cubes; i++) {
                result += random.nextInt(6) + 1;
            }
            return result;
        }

    }

    private final int SCORE_TO_WIN = 7;

    private final Player[] players;
    private final int cubes; // amount of cubes
    private Player winner;

    public DiceGame(int players, int cubes) {
        this.players = new Player[players + 1]; //+PC

        for (int i = 0; i < this.players.length - 1; i++) {
            this.players[i] = new DicePlayer(i + 1);
        }

        this.players[players] = new ComputerPlayer(players + 1); // PC is not real
        this.cubes = cubes;
    }

    // ************************************************************************
    @Override
    public Player getWinner() {
        return winner;
    }

    /**
     * Main class method.
     */
    public void play() {
        Dice dices = this.new Dice();

        while (winner == null) { // main body
            System.out.println("-----------------toss!-----------------");

            for (Player player : players) {
                player.setTurnResult(dices.roll());
                System.out.println(player + ": the number " + player.getTurnResult() + " has been dropped out on the dice");
            }

            endRound();
            winner = findWinner();
        }

        System.out.println(winner + " won the game");  // someone won
    }


    private Player findWinner() {
        for (Player player : players) {
            if (player.getScore() == SCORE_TO_WIN) {
                return player;
            }
        }
        return null;
    }


    /**
     * This method ends round correctly. It prints who has won round, sets scores,
     * prints it and moves array of players.
     */
    private void endRound() {
        int firstWinner = indexMax();
        System.out.println(players[firstWinner] + " won this round!");

        players[firstWinner].setScore(players[firstWinner].getScore() + 1);

        printScores();
        moveArray(players.length - firstWinner);
    }

    /**
     * Method prints scores.
     */
    @Override
    public void printScores() {
        System.out.println("-----------------scores-----------------");
        for (Player player : players) {
            System.out.println(player + ": " + player.getScore());
        }
        System.out.println("----------------------------------------");
    }


    /**
     * @return index of player, who had the highest toss result.
     */
    private int indexMax() {

        int max = 0;
        int index = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getTurnResult() > max) {
                index = i;
                max = players[i].getTurnResult();
            }
        }
        return index;
    }

    /**
     * Method moves array after round so that the first position will be the winner.
     */
    private void moveArray(int positions) {
        int size = players.length;
        for (int i = 0; i < positions; i++) {
            Player temp = players[size - 1];
            for (int j = size - 1; j > 0; j--) {
                players[j] = players[j - 1];
            }
            players[0] = temp;
        }
    }

}
