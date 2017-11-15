package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.RandomGenerator;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.ka.KaVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.AddressProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.locale.ka.KaAddressProvider;
import io.codearte.jfairy.producer.person.locale.ka.KaNationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.ka.KaPassportNumberProvider;

public class KaFairyModule extends FairyModule {

	public KaFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(KaNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(KaVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(KaAddressProvider.class);
		bind(PassportNumberProvider.class).to(KaPassportNumberProvider.class);
	}
}
