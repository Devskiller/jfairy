package com.devskiller.jfairy;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.RandomGenerator;
import com.devskiller.jfairy.producer.VATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.company.locale.sk.SkVATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.person.AddressProvider;
import com.devskiller.jfairy.producer.person.NationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.NationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.PassportNumberProvider;
import com.devskiller.jfairy.producer.person.locale.sk.SkAddressProvider;
import com.devskiller.jfairy.producer.person.locale.sk.SkNationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.locale.sk.SkNationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.locale.sk.SkPassportNumberProvider;

public class SkFairyModule extends FairyModule {

	public SkFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(SkNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(SkNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(SkVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(SkAddressProvider.class);
		bind(PassportNumberProvider.class).to(SkPassportNumberProvider.class);
	}

}
