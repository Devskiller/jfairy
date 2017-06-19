package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.de.DeVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.AddressProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.locale.de.DeAddressProvider;
import io.codearte.jfairy.producer.person.locale.de.DeNationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.de.DePassportNumberProvider;

import java.util.Random;

/**
 * @author Roland Weisleder
 */
public class DeFairyModule extends FairyModule {

	public DeFairyModule(DataMaster dataMaster, Random random) {
		super(dataMaster, random);
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
