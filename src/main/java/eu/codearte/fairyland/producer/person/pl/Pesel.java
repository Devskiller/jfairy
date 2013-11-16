package eu.codearte.fairyland.producer.person.pl;

import eu.codearte.fairyland.producer.person.NationalIdentificationNumber;
import eu.codearte.fairyland.producer.person.Sex;
import eu.codearte.fairyland.producer.util.DateGenerator;
import eu.codearte.fairyland.producer.util.RandomGenerator;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;

/**
 * PESEL - Polish Powszechny Elektroniczny System Ewidencji Ludności,
 * Universal Electronic System for Registration of the Population)
 * <p/>
 * More info: http://en.wikipedia.org/wiki/PESEL
 */
public class Pesel implements NationalIdentificationNumber {

	private static final int[] WEIGHTS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

	private final RandomGenerator randomGenerator;
	private final DateGenerator dateGenerator;

	@Inject
	public Pesel(DateGenerator dateGenerator, RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
		this.dateGenerator = dateGenerator;
	}

	@Override
	public String generate() {

		DateTime date = dateGenerator.randomDateInThePast(10);
		Sex sex = randomGenerator.trueOrFalse() ? Sex.MALE : Sex.FEMALE;

		return generate(date, sex);
	}

	@Override
	public String generate(DateTime date, Sex sex) {
		int year = date.getYearOfCentury();
		int month = calculateMonth(date.getMonthOfYear(), date.getYear());
		int day = date.getDayOfMonth();
		int serialNumber = randomGenerator.randomBetween(0, 999);
		int sexCode = calculateSexCode(sex);

		String pesel = format("%02d%02d%02d%03d%d", year, month, day, serialNumber, sexCode);

		return pesel + calculateChecksum(pesel);
	}

	/**
	 * @param pesel
	 * @return
	 */
	public static boolean isValid(String pesel) {
		int size = pesel.length();
		if (size != 11) {
			return false;
		}

		int checksum = valueOf(pesel.substring(size - 1));
		int checkDigit = calculateChecksum(pesel);

		return checkDigit == checksum;

	}

	private int calculateMonth(int month, int year) {
		int peselMonth = month;
		if (year >= 1800 && year < 1900) {
			peselMonth += 80;
		} else if (year >= 2000 && year < 2100) {
			peselMonth += 20;
		} else if (year > 2100 && year < 2200) {
			peselMonth += 40;
		} else if (year > 2200 && year < 2300) {
			peselMonth += 60;
		}
		return peselMonth;
	}

	private int calculateSexCode(Sex sex) {
		return randomGenerator.randomBetween(0, 4) * 2 + (sex == Sex.MALE ? 1 : 0);
	}

	private static int calculateChecksum(String pesel) {
		int sum = 0, checksum;
		int i = 0;
		for (int weight : WEIGHTS) {
			int digit = (int) pesel.charAt(i);
			sum += digit * weight;
		}
		checksum = 10 - (sum % 10);
		return checksum % 10;
	}

}
