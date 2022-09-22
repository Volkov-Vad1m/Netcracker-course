package dicegame;

import java.util.ArrayList;
import java.util.List;

public class Dice {
    private final int roundsToWins = 7;

    private Player[] players;
    private int cubes; // amount of cubes


    public Dice(int players, int cubes) {
        this.players = new Player[players + 1]; //+PC

        for(int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player();
        }

        this.players[players].setReal(false); // PC is not real

        this.cubes = cubes;
    }
    // ************************************************************************

    /**
     * Main class method.
     */
    public void play() {

        while(!someoneWon()) {
            System.out.println("-----------------toss!-----------------");
            for (Player player : players) {
                player.tossesCubes(cubes);

                // it is PC
                if (player.isReal()) {
                    System.out.print("Player " + player.getId() + ": ");
                } else {
                    System.out.print("PC: ");
                }
                System.out.println("the number " + player.getTossResult() + " has been dropped out on the dice");
            }

            endRound();
        }

        for(Player player : players) {
            if(player.getScore() == 7) {
                if(player.isReal()) {
                    System.out.println("Player " + player.getId() + " won the game");
                } else {
                    System.out.println("PC won the game");
                }
            }
        }

    }

    /**
     * @return true - if someone has won the game
     */
    private boolean someoneWon() {
        for(Player a : players) {
            if(a.getScore() == roundsToWins) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method ends round correctly. It prints who has won, sets scores, prints it and moves array of players.
     */
    private void endRound() {
        int firstWinner = indexMax();

        if (players[firstWinner].isReal()) {
            System.out.println("Player " + (players[firstWinner].getId()) + " won!");
        } else {
            System.out.println("PC won!");
        }

        players[firstWinner].setScore(players[firstWinner].getScore() + 1);

        dumpScores();
        moveArray(players.length - firstWinner);
    }

    /**
     * @return index of player, who had the highest toss result
     */
    private int indexMax() {

        int max = 0;
        int index = 0;
        for(int i = 0; i < players.length; i++) {
            if(players[i].getTossResult() > max) {
                index = i;
                max = players[i].getTossResult();
            }
        }
        return index;
    }

    /**
     * method prints scores
     */
    private void dumpScores() {
        System.out.println("-----------------scores-----------------");
        for(Player player: players) {
            if(player.isReal()) {
                System.out.println("Player " + player.getId() + ": " + player.getScore());
            } else {
                System.out.println("PC: " + player.getScore());
            }
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Method moves array after round so that the first position will be the winner.
     */
    private void moveArray(int positions) {
        int size = players.length;
        for (int i = 0; i < positions; i++) {
            Player temp = players[size - 1];
            for (int j = size - 1; j > 0; j--) {
                players[j] = players[j-1];
            }
            players[0] = temp;
        }
    }

}
