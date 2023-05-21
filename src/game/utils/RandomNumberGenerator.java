package game.utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class RandomNumberGenerator {

    /**
     * Static method to get the random integer
     * @param bound
     * @return random integer
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Static method to get the random integer
     * @param lowerBound
     * @param upperBound
     * @return random integer
     */

    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
