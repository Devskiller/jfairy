package com.devskiller.jfairy;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.RandomGenerator;
import com.devskiller.jfairy.producer.VATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.company.locale.br.BrVATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.person.AddressProvider;
import com.devskiller.jfairy.producer.person.NationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.NationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.PassportNumberProvider;
import com.devskiller.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.locale.br.BrAddressProvider;
import com.devskiller.jfairy.producer.person.locale.br.BrNationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.locale.br.BrPassportNumberProvider;

/**
 * @author Nelson Rodrigues da Silva Júnior
 * @since 15.10.2020
 */
public class BrFairyModule extends FairyModule {

	public BrFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(BrNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(BrVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(BrAddressProvider.class);
		bind(PassportNumberProvider.class).to(BrPassportNumberProvider.class);
	}

}
