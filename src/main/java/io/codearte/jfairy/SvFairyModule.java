package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.sv.SvVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.sv.PersonalIdentityNumberProvider;
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
		bind(NationalIdentityCardNumberProvider.class).to(PersonalIdentityNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(SvVATIdentificationNumberProvider.class);
		bind(PassportNumberProvider.class).to(SvPassportNumberProvider.class);
		bind(CharConverter.class).to(SvCharConverter.class);
	}

}
