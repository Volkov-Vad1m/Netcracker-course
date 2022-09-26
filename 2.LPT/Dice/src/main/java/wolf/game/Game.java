package wolf.game;

import wolf.player.Player;

public interface Game {

    void play();

    void printScores();

    Player getWinner();
}