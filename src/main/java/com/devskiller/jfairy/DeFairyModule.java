package com.devskiller.jfairy;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.RandomGenerator;
import com.devskiller.jfairy.producer.VATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.company.locale.de.DeVATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.person.AddressProvider;
import com.devskiller.jfairy.producer.person.NationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.NationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.PassportNumberProvider;
import com.devskiller.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.locale.de.DeAddressProvider;
import com.devskiller.jfairy.producer.person.locale.de.DeNationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.locale.de.DePassportNumberProvider;

/**
 * @author Roland Weisleder
 */
public class DeFairyModule extends FairyModule {

	public DeFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(DeNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(DeVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(DeAddressProvider.class);
		bind(PassportNumberProvider.class).to(DePassportNumberProvider.class);
	}

}
