package eu.codearte.fairyland.producer;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class RandomGenerator {

	private static final int SEED = 1761283695;

	private final Random random;

	@Inject
	public RandomGenerator() {
		this.random = new Random(SEED);
	}

	public boolean getBoolean() {
		return random.nextBoolean();
	}

	public int getInteger(int max) {
		return random.nextInt(max);
	}

	public long getLong(long max) {
		return (long) random.nextDouble() * max;
	}

	public void shuffle(List<?> elements) {
		Collections.shuffle(elements, random);
	}


	//TODO: MZA: Duplication - should be merged into one method when consistent logic will be determined
	public char randomBetween(char min, char max) {
		return (char) randomBetween((int) min, (int) max);
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
		int randomInt = range > 0 ? this.random.nextInt(range) : 0;
		return min + randomInt;
	}

	/**
	 * Returns random long value from a range (including both range boundaries).
	 * <p/>
	 * It required to satisfied condition min <= max.
	 *
	 * @param min lower bound of a range
	 * @param max higher bound of a range
	 * @return pseudorandom {@code long} value between {@code mon} (inclusive) and {@code max} (inclusive)
	 */
	public long randomBetween(long min, long max) {
		Preconditions.checkArgument(min <= max, "%s has to be <= %s", min, max);
		//Can it be done easier for long numbers?
		long range = (max - min) + 1;
		return min + (long) (random.nextDouble() * range);
	}
}
