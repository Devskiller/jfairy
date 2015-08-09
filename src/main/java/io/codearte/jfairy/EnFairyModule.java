package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.en.EmployerIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.en.EnPassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.en.SocialSecurityCardNumberProvider;
import io.codearte.jfairy.producer.util.CharConverter;
import io.codearte.jfairy.producer.util.locale.NonOpCharConverter;

import java.util.Random;

/**
 * @author Olga Maciaszek-Sharma
 * @since 08.03.15
 */
public class EnFairyModule extends FairyModule {

	public EnFairyModule(DataMaster dataMaster, Random random) {
		super(dataMaster, random);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentityCardNumberProvider.class).to(SocialSecurityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(EmployerIdentificationNumberProvider.class);
		bind(PassportNumberProvider.class).to(EnPassportNumberProvider.class);
		bind(CharConverter.class).to(NonOpCharConverter.class);
	}

}
