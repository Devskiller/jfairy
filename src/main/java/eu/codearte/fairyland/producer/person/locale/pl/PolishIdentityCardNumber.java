package eu.codearte.fairyland.producer.person.locale.pl;

import eu.codearte.fairyland.producer.BaseProducer;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;
import eu.codearte.fairyland.producer.util.DateGenerator;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;
import static eu.codearte.fairyland.producer.util.AlphaNumberSystem.convertToString;
import static java.lang.String.copyValueOf;
import static java.lang.String.valueOf;
import static java.lang.System.arraycopy;
import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * Polish Identity Card Number
 */
public class PolishIdentityCardNumber implements NationalIdentityCardNumber {

	private static final int[] WEIGHTS = new int[]{7, 3, 1, 0, 7, 3, 1, 7, 3};
	public static final int BEGIN = 2000;
	public static final int PREFIXES_BY_YEAR = 45;

	private final DateGenerator dateGenerator;
	private final BaseProducer baseProducer;

	@Inject
	public PolishIdentityCardNumber(DateGenerator dateGenerator, BaseProducer baseProducer) {
		this.dateGenerator = dateGenerator;
		this.baseProducer = baseProducer;
	}

	@Override
	public String generate() {

		DateTime dateTime = dateGenerator.randomDateBetweenYearAndNow(BEGIN);

		return generate(dateTime);
	}

	@Override
	public String generate(DateTime date) {

		checkArgument(date.getYear() >= BEGIN, "Polish ID was introduced in 2000");

		char[] id = new char[WEIGHTS.length];

		fillAlphaPrefix(date.getYear(), id);
		fillDigits(id);

		char checksum = calculateChecksum(id);

		id[3] = checksum;

		return copyValueOf(id);

	}

	public boolean isValid(String id) {
		int checksum = calculateChecksum(id.toCharArray());

		return id.charAt(3) == checksum;
	}

	private char calculateChecksum(char[] id) {
		int index = 0;
		int checksum = 0;

		for (int weight : WEIGHTS) {
			int value = 0;
			if (index < 3) {
				value = id[index] - 'A' + 10;
			} else if (index > 3) {
				value = id[index] - '0';
			}
			index++;
			checksum += weight * value;
		}

		return valueOf(checksum % 10).charAt(0);
	}

	private void fillDigits(char[] id) {
		String num = valueOf(baseProducer.randomBetween(0, 99999));
		char[] digits = leftPad(num, 5, '0').toCharArray();
		arraycopy(digits, 0, id, 4, digits.length);
	}

	private void fillAlphaPrefix(int year, char[] id) {
		int maxPrefix = (year - BEGIN) * PREFIXES_BY_YEAR;
		int range = baseProducer.randomBetween(maxPrefix, maxPrefix + PREFIXES_BY_YEAR);
		String prefix = convertToString(range, 26);
		char[] charArray = leftPad(prefix, 3, 'A').toCharArray();
		arraycopy(charArray, 0, id, 0, charArray.length);
	}

}
