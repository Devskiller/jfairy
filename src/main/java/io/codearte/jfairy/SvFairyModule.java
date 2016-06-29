package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.sv.SvVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.AddressProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.sv.PersonalIdentityNumberFactory;
import io.codearte.jfairy.producer.person.locale.sv.SvAddressProvider;
import io.codearte.jfairy.producer.person.locale.sv.SvNationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.sv.SvPassportNumberProvider;
import io.codearte.jfairy.producer.util.CharConverter;
import io.codearte.jfairy.producer.util.locale.SvCharConverter;

import java.util.Random;

public class SvFairyModule extends FairyModule {

	public SvFairyModule(DataMaster dataMaster, Random random) {
		super(dataMaster, random);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(PersonalIdentityNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(SvNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(SvVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(SvAddressProvider.class);
		bind(PassportNumberProvider.class).to(SvPassportNumberProvider.class);
		bind(CharConverter.class).to(SvCharConverter.class);
	}

}
