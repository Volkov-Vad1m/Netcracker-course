package dicegame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("type number of players");
        int players = in.nextInt();

        System.out.println("How many cubes?");
        int cubes = in.nextInt();
        Dice dealer = new Dice(players, cubes);
        dealer.play();
    }
}
