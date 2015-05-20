package io.codearte.jfairy.dataProvider.person.locale.pl;

import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.dataProvider.BaseProducer;
import io.codearte.jfairy.dataProvider.DateProducer;
import io.codearte.jfairy.dataProvider.person.NationalIdentificationNumber;
import io.codearte.jfairy.dataProvider.person.Person;
import org.joda.time.DateTime;

import javax.inject.Inject;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;

/**
 * PESEL - Polish Powszechny Elektroniczny System Ewidencji Ludności,
 * Universal Electronic System for Registration of the Population)
 * More info: http://en.wikipedia.org/wiki/PESEL
 */
public class PeselProvider implements Provider<NationalIdentificationNumber> {

	private static final int PESEL_LENGTH = 11;
	private static final int VALIDITY_IN_YEARS = 10;

	private static final int[] PERIOD_WEIGHTS = {80, 0, 20, 40, 60};
	private static final int PERIOD_FACTOR = 100;
	private static final int BEGIN_YEAR = 1800;

	private static final int[] WEIGHTS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
	private static final int MAX_SERIAL_NUMBER = 999;
	private static final int TEN = 10;

	private static final int[] SEX_FIELDS = {0, 2, 4, 6, 8};

	private final BaseProducer baseProducer;
	private final DateProducer dateProducer;
	private DateTime issueDate;
	private Person.Sex sex;

	@Inject
	public PeselProvider(DateProducer dateProducer, BaseProducer baseProducer,
	                     @Assisted
	                     PeselProperties.Property... properties) {
		this.dateProducer = dateProducer;
		this.baseProducer = baseProducer;

		with(properties);

	}

	public void with(PeselProperties.Property[] properties) {
		for (PeselProperties.Property property : properties) {
			property.apply(this);
		}
	}

	@Override
	public NationalIdentificationNumber get() {

		if (issueDate == null) {
			issueDate = dateProducer.randomDateInThePast(VALIDITY_IN_YEARS);
		}
		if (sex == null) {
			sex = baseProducer.trueOrFalse() ? Person.Sex.MALE : Person.Sex.FEMALE;
		}

		return new NationalIdentificationNumber(generate());
	}

	private String generate() {
		int year = issueDate.getYearOfCentury();
		int month = calculateMonth(issueDate.getMonthOfYear(), issueDate.getYear());
		int day = issueDate.getDayOfMonth();
		int serialNumber = baseProducer.randomInt(MAX_SERIAL_NUMBER);
		int sexCode = calculateSexCode(sex);

		String pesel = format("%02d%02d%02d%03d%d", year, month, day, serialNumber, sexCode);

		return pesel + calculateChecksum(pesel);
	}

	public void setIssueDate(DateTime issueDate) {
		this.issueDate = issueDate;
	}

	public void setSex(Person.Sex sex) {
		this.sex = sex;
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
		int sum = 0;
		int i = 0;
		for (int weight : WEIGHTS) {
			int digit = Character.digit(pesel.charAt(i++), 10);
			sum += digit * weight;
		}

		int checksum = (sum % TEN);

		if (0 == checksum) {
			return 0;
		}

		return TEN - checksum;
	}
}
