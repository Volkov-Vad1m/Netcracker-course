package dicegame;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    private Player[] players; //including pc
    private int cubes;


    public Dice(int players, int cubes) {
        this.players = new Player[players + 1]; //+PC

        for(int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player();
        }

        this.players[players].setReal(false); // PC is not real

        this.cubes = cubes;
    }
////////////////////////////////////////////////////////////////
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
                System.out.println("the number " + player.getTossResult() + " dropped out on the dice");
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
            if(a.getScore() == 7) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method ends round correctly.
     */
    private void endRound() {
        int firstWinner = indexMax();

        if (players[firstWinner].isReal()) {
            System.out.println("Player " + (players[firstWinner].getId()) + " won!");
        } else {
            System.out.print("PC won!");
        }

        players[firstWinner].setScore(players[firstWinner].getScore() + 1);

        dumpScores();
        moveRight(players.length - firstWinner);
    }

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

    private void dumpScores(){
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

    private void moveRight(int positions) {
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
