package me.torabi.gameoflife;

import java.util.Random;

/**
 * Created by maysam on 5/18/15.
 */
public class Game {
    private boolean[][] numbers;
    Random rand = new Random(System.currentTimeMillis());

    public boolean[][] next() {
        boolean[][] tmp = new boolean[numbers.length][numbers[0].length];
        for(int i=0; i<numbers.length; i++) {
            for (int j=0; j<numbers[i].length; j++) {
                tmp[i][j] = rand.nextBoolean();
            }
        }
        numbers = tmp;
        return numbers;
    }

    public boolean[][] init(int width, int height) {
        numbers = new boolean[width][height];
        for(int i=0; i<numbers.length; i++) {
            for (int j=0; j<numbers[i].length; j++) {
                numbers[i][j] = rand.nextBoolean();
            }
        }
        return numbers;
    }
}
