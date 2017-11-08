package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.RandomGenerator;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.ge.GeVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.AddressProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.locale.ge.GeAddressProvider;
import io.codearte.jfairy.producer.person.locale.ge.GeNationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.ge.GePassportNumberProvider;

public class GeFairyModule extends FairyModule {

	public GeFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(GeNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(GeVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(GeAddressProvider.class);
		bind(PassportNumberProvider.class).to(GePassportNumberProvider.class);
	}
}
