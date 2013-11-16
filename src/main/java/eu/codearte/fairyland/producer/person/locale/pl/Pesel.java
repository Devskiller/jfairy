package eu.codearte.fairyland.producer.person.locale.pl;

import eu.codearte.fairyland.producer.BaseProducer;
import eu.codearte.fairyland.producer.person.NationalIdentificationNumber;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.util.DateGenerator;
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

	private static final int PESEL_LENGTH = 11;
	private static final int VALIDITY_IN_YEARS = 10;

	private static final int[] PERIOD_WEIGHTS = {80, 0, 20, 40, 60};
	private static final int PERIOD_FACTOR = 100;
	private static final int BEGIN_YEAR = 1800;

	private static final int[] WEIGHTS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
	private static final int MIN_SERIAL_NUMBER = 0;
	private static final int MAX_SERIAL_NUMBER = 999;

	private static final int TEN = 10;

	private final DateGenerator dateGenerator;
	private final BaseProducer baseProducer;

	@Inject
	public Pesel(DateGenerator dateGenerator, BaseProducer baseProducer) {
		this.dateGenerator = dateGenerator;
		this.baseProducer = baseProducer;
	}

	@Override
	public String generate() {

		DateTime date = dateGenerator.randomDateInThePast(VALIDITY_IN_YEARS);
		Person.Sex sex = baseProducer.trueOrFalse() ? Person.Sex.MALE : Person.Sex.FEMALE;

		return generate(date, sex);
	}

	@Override
	public String generate(DateTime date, Person.Sex sex) {
		int year = date.getYearOfCentury();
		int month = calculateMonth(date.getMonthOfYear(), date.getYear());
		int day = date.getDayOfMonth();
		int serialNumber = baseProducer.randomBetween(MIN_SERIAL_NUMBER, MAX_SERIAL_NUMBER);
		int sexCode = calculateSexCode(sex);

		String pesel = format("%02d%02d%02d%03d%d", year, month, day, serialNumber, sexCode);

		return pesel + calculateChecksum(pesel);
	}

	/**
	 * @param pesel
	 * @return pesel validity
	 */
	public static boolean isValid(String pesel) {
		int size = pesel.length();
		if (size != PESEL_LENGTH) {
			return false;
		}

		int checksum = valueOf(pesel.substring(size - 1));
		int checkDigit = calculateChecksum(pesel);

		return checkDigit == checksum;

	}

	private int calculateMonth(int month, int year) {
		return month + PERIOD_WEIGHTS[(year - BEGIN_YEAR) / PERIOD_FACTOR];
	}

	private int calculateSexCode(Person.Sex sex) {
		return baseProducer.randomBetween(0, 4) * 2 + (sex == Person.Sex.MALE ? 1 : 0);
	}

	private static int calculateChecksum(String pesel) {
		int sum = 0, checksum;
		int i = 0;
		for (int weight : WEIGHTS) {
			int digit = (int) pesel.charAt(i);
			sum += digit * weight;
		}
		checksum = TEN - (sum % TEN);
		return checksum % TEN;
	}

}
