/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class RandomGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(RandomGenerator.class);
    private final Random random;

    public RandomGenerator(long seed) {
        random = new Random(seed);
    }

    public boolean trueOrFalse() {
        return random.nextBoolean();
    }

    public String randomElement(List<String> elements) {
        return elements.get(randomBetween(0, elements.size() - 1));
    }

    public List<String> randomElements(List<String> elements, int count) {
        shuffle(elements, random);
        return elements.subList(0, count % elements.size());
    }

    /**
     * Returns random int value
     *
     * @param max value of the random number to be returned.  Must be
     *            positive.
     * @return random {@code int} value between {@code 0} (inclusive) and {@code max} (inclusive)
     */
    public int randomBetween(int min, int max) {
        int range = max - min + 1;
        int random = range > 0 ? this.random.nextInt(range) : 0;
        return min + random;
    }

    public char randomBetween(char min, char max) {
        int range = max - min + 1;
        int random = range > 0 ? this.random.nextInt(range) : 0;
        return (char) (min + random);
    }

}
