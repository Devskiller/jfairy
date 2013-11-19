package eu.codearte.fairyland.producer;

import com.google.common.base.Preconditions;

import javax.inject.Singleton;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

@Singleton
public class BaseProducer {

	private static final int SEED = 1761283695;

	private final Random random;

	public BaseProducer() {
		this(SEED);
	}

	public BaseProducer(int seed) {
		this.random = new Random(seed);
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
		int randomInt = range > 0 ? this.random.nextInt(range) : 0;
		return min + randomInt;
	}

	public int randomInt(int max) {
		return randomBetween(0, max);
	}

	//TODO: MZA: Duplication - should be merged into one method when consistent logic will be determined
	public char randomBetween(char min, char max) {
		return (char) randomBetween((int) min, (int) max);
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

  /**
   * Returns random double value
   *
   * @param max value of the random number to be returned.  Must be
   *            positive.
   * @return random {@code double} value between {@code 0} (inclusive) and {@code max} (inclusive)
   */
  public double randomBetween(double min, double max) {
    double range = max - min;
    double randomDouble = range > 0 ? this.random.nextDouble()*range : 0;
    return min + randomDouble;
  }

	public String letterify(String letterString) {
		return letterify(letterString, 'a', 'z');
	}

  public String letterify(String letterString, char from, char to) {
    return replaceSymbolWithCharsFromTo(letterString, '?', from, to);
  }

	public String numerify(String numberString) {
		return replaceSymbolWithCharsFromTo(numberString, '#', '0', '9');
	}

	public String bothify(String string) {
		return letterify(numerify(string));
	}

	private String replaceSymbolWithCharsFromTo(String string, char symbol, char from, char to) {
		StringBuilder result = new StringBuilder();
		for (char aChar : string.toCharArray()) {
			if (aChar == symbol) {
				result.append(randomBetween(from, to));
			} else {
				result.append(aChar);
			}
		}
		return result.toString();
	}
}
