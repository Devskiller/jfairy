package eu.codearte.fairyland.producer;

import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RandomProducer {

	private final RandomGenerator random;
	private final FakerProducer fakerProducer;
	private final DateGenerator dateGenerator;

	@Inject
	public RandomProducer(RandomGenerator random, DateGenerator dateGenerator, FakerProducer fakerProducer) {
		this.random = random;
		this.dateGenerator = dateGenerator;
		this.fakerProducer = fakerProducer;
	}

	public DateTime randomDateInThePast(int maxYearsEarlier) {
		return dateGenerator.randomDateInThePast(maxYearsEarlier);
	}

	public DateTime randomDateBetweenYearAndNow(int fromYear) {
		return dateGenerator.randomDateBetweenYearAndNow(fromYear);
	}

	public boolean trueOrFalse() {
		return random.getBoolean();
	}

	public String randomElement(List<String> elements) {
		return elements.get(randomBetween(0, elements.size() - 1));
	}

	public List<String> randomElements(List<String> elements, int count) {
		random.shuffle(elements);
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
		return random.randomBetween(min, max);
	}

	public int randomWithMax(int max) {
		return randomBetween(0, max);
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
		return random.randomBetween(min, max);
	}

	// FIXME: Should be moved from here
	public String letterify(String letterString) {
		return fakerProducer.letterify(letterString);
	}

	public String numerify(String numberString) {
		return fakerProducer.numerify(numberString);
	}

	public String bothify(String string) {
		return fakerProducer.bothify(string);
	}

}
