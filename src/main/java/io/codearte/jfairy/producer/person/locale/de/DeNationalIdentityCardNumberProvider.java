package io.codearte.jfairy.producer.person.locale.de;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;

import javax.inject.Inject;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * German Identity Card (known as Personalausweis)
 * <p>
 * https://en.wikipedia.org/wiki/German_identity_card
 *
 * @author Roland Weisleder
 */
public class DeNationalIdentityCardNumberProvider implements NationalIdentityCardNumberProvider {

	private final BaseProducer baseProducer;

	@Inject
	public DeNationalIdentityCardNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	@Override
	public String get() {
		return baseProducer.randomElement("L", "M", "N", "P", "R", "T", "V", "W", "X", "Y") + randomNumeric(8);
	}

	public boolean isValid(String nationalIdentityCardNumber) {
		return nationalIdentityCardNumber.matches("^[LMNPRTVWXY][0-9]{8}$");
	}

}
