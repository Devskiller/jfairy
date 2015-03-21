package io.codearte.jfairy;

import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.en.EmployerIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.en.EnPassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.en.SocialSecurityCardNumberProvider;

import java.util.Random;

/**
 * @author omaciaszek
 * @since 08.03.15
 */
public class EnFairyModule extends FairyModule {

	public EnFairyModule(Random random) {
		super(random);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentityCardNumberProvider.class).to(SocialSecurityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(EmployerIdentificationNumberProvider.class);
		bind(PassportNumberProvider.class).to(EnPassportNumberProvider.class);

	}

}
