package com.devskiller.jfairy;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.RandomGenerator;
import com.devskiller.jfairy.producer.VATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.company.locale.pl.PlVATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.person.AddressProvider;
import com.devskiller.jfairy.producer.person.NationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.NationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.PassportNumberProvider;
import com.devskiller.jfairy.producer.person.locale.pl.PlAddressProvider;
import com.devskiller.jfairy.producer.person.locale.pl.PlNationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.locale.pl.PlNationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.locale.pl.PlPassportNumberProvider;

/**
 * @author Olga Maciaszek-Sharma
 * @since 21.02.15
 */
public class PlFairyModule extends FairyModule {

	public PlFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(PlNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(PlNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(PlVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(PlAddressProvider.class);
		bind(PassportNumberProvider.class).to(PlPassportNumberProvider.class);
	}
}
