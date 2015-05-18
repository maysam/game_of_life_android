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
        for(int i=1; i<numbers.length-1; i++) {
            for (int j=1; j<numbers[i].length-1; j++) {
                tmp[i][j] = live(numbers, i,j);
            }
        }
        numbers = tmp;
        return numbers;
    }

    private boolean live(boolean[][] nums, int i, int j) {
        int live_neighbors = 0;
        for (int k = i-1; k <= i+1; k++) {
            for (int l = j-1; l <= j+1; l++) {
                if (nums[k][l] & ((k!=i) | (l!=j))) {
                    live_neighbors++;
                }
            }
        }
        if (nums[i][j]) {
            if(live_neighbors < 2)
                return false;
            if(live_neighbors > 3)
                return false;
        } else {
            if(live_neighbors == 3)
                return true;
        }
        return nums[i][j];
    }

    public boolean[][] init(int width, int height) {
        numbers = new boolean[width][height];
        for(int i=0; i<numbers.length; i++) {
            for (int j=0; j<numbers[i].length; j++) {
                numbers[i][j] = rand.nextBoolean() & (i>0) & (i<numbers.length-1) & (j>0) & (j<numbers[0].length-1);
            }
        }
        return numbers;
    }
}
