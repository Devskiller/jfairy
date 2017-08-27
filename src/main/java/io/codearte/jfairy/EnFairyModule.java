package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.RandomGenerator;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.en.EnVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.AddressProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.locale.en.EnAddressProvider;
import io.codearte.jfairy.producer.person.locale.en.EnPassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.en.EnNationalIdentityCardNumberProvider;

import java.util.Random;

/**
 * @author Olga Maciaszek-Sharma
 * @since 08.03.15
 */
public class EnFairyModule extends FairyModule {

	public EnFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(EnNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(EnVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(EnAddressProvider.class);
		bind(PassportNumberProvider.class).to(EnPassportNumberProvider.class);
	}

}
