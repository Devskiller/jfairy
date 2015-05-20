package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.dataProvider.VATIdentificationNumberProvider;
import io.codearte.jfairy.dataProvider.company.locale.en.EmployerIdentificationNumberProvider;
import io.codearte.jfairy.dataProvider.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.dataProvider.person.PassportNumberProvider;
import io.codearte.jfairy.dataProvider.person.locale.en.EnPassportNumberProvider;
import io.codearte.jfairy.dataProvider.person.locale.en.SocialSecurityCardNumberProvider;

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

	}

}
