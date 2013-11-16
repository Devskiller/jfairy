package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.util.RandomGenerator;

import static eu.codearte.fairyland.producer.person.Sex.FEMALE;
import static eu.codearte.fairyland.producer.person.Sex.MALE;

/**
 * @author mariuszs
 * @since 03.11.13.
 */
public class Config {

	private final RandomGenerator random;

	private Sex sex;
	private String telephoneNumberFormat;

	public Config(RandomGenerator random) {
		this.random = random;
		sex = randomSex();
	}

	private Sex randomSex() {
		return random.trueOrFalse() ? MALE : FEMALE;
	}

	public Sex sex() {
		return sex;
	}

	public String getTelephoneNumberFormat() {
		return telephoneNumberFormat;
	}

	public void applyTelephoneNumberFormat(String telephoneNumberFormat) {
		this.telephoneNumberFormat = telephoneNumberFormat;
	}

	public void applySex(Sex sex) {
		this.sex = sex;
	}
}
