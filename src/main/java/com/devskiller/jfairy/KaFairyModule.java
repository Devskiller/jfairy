package com.devskiller.jfairy;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.RandomGenerator;
import com.devskiller.jfairy.producer.VATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.company.locale.ka.KaVATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.person.AddressProvider;
import com.devskiller.jfairy.producer.person.NationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.NationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.PassportNumberProvider;
import com.devskiller.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.locale.ka.KaAddressProvider;
import com.devskiller.jfairy.producer.person.locale.ka.KaNationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.locale.ka.KaPassportNumberProvider;

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
