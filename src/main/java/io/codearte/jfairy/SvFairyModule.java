package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.sv.MomsnrProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
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
		bind(NationalIdentityCardNumberProvider.class).to(SvNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(MomsnrProvider.class);
		bind(PassportNumberProvider.class).to(SvPassportNumberProvider.class);
		bind(CharConverter.class).to(SvCharConverter.class);
	}

}
