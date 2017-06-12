package io.codearte.jfairy.producer.person.locale.de;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.PassportNumberProvider;

import javax.inject.Inject;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * German passport (known as Reisepass)
 * <p>
 * https://en.wikipedia.org/wiki/German_passport
 *
 * @author Roland Weisleder
 */
public class DePassportNumberProvider implements PassportNumberProvider {

	private final BaseProducer baseProducer;

	@Inject
	public DePassportNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	@Override
	public String get() {
		return baseProducer.randomElement("C", "F", "G", "H", "J", "K") + randomNumeric(8);
	}

	public boolean isValid(String passportNumber) {
		return passportNumber.matches("^[CFGHJK][0-9]{8}$");
	}

}
