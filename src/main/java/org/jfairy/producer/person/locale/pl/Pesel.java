package org.jfairy.producer.person.locale.pl;

import org.jfairy.producer.BaseProducer;
import org.jfairy.producer.DateProducer;
import org.jfairy.producer.person.NationalIdentificationNumber;
import org.jfairy.producer.person.Person;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;

/**
 * PESEL - Polish Powszechny Elektroniczny System Ewidencji Ludności,
 * Universal Electronic System for Registration of the Population)
 *
 * More info: http://en.wikipedia.org/wiki/PESEL
 */
public class Pesel implements NationalIdentificationNumber {

	private static final int PESEL_LENGTH = 11;
	private static final int VALIDITY_IN_YEARS = 10;

	private static final int[] PERIOD_WEIGHTS = {80, 0, 20, 40, 60};
	private static final int PERIOD_FACTOR = 100;
	private static final int BEGIN_YEAR = 1800;

	private static final int[] WEIGHTS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
	private static final int MAX_SERIAL_NUMBER = 999;
	private static final int TEN = 10;

	private static final int[] SEX_FIELDS = {0, 2, 4, 6, 8};

	private final DateProducer dateProducer;
	private final BaseProducer baseProducer;

	@Inject
	public Pesel(DateProducer dateProducer, BaseProducer baseProducer) {
		this.dateProducer = dateProducer;
		this.baseProducer = baseProducer;
	}

	@Override
	public String generate() {

		DateTime date = dateProducer.randomDateInThePast(VALIDITY_IN_YEARS);
		Person.Sex sex = baseProducer.trueOrFalse() ? Person.Sex.MALE : Person.Sex.FEMALE;

		return generate(date, sex);
	}

	@Override
	public String generate(DateTime date, Person.Sex sex) {
		int year = date.getYearOfCentury();
		int month = calculateMonth(date.getMonthOfYear(), date.getYear());
		int day = date.getDayOfMonth();
		int serialNumber = baseProducer.randomInt(MAX_SERIAL_NUMBER);
		int sexCode = calculateSexCode(sex);

		String pesel = format("%02d%02d%02d%03d%d", year, month, day, serialNumber, sexCode);

		return pesel + calculateChecksum(pesel);
	}

	/**
	 * @param pesel PESEL
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

	// This should be tested
	private int calculateSexCode(Person.Sex sex) {
		return SEX_FIELDS[baseProducer.randomInt(SEX_FIELDS.length - 1)] + (sex == Person.Sex.MALE ? 1 : 0);
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
