package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.pl.NIPProvider;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.pl.PlIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.pl.PlPassportNumberProvider;

import java.util.Optional;
import java.util.Random;

/**
 * @author omaciaszek
 * @since 21.02.15
 */
public class PlFairyModule extends FairyModule {

	public PlFairyModule(Optional<DataMaster> dataMaster, Random random) {
		super(dataMaster, random);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentityCardNumberProvider.class).to(PlIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(NIPProvider.class);
		bind(PassportNumberProvider.class).to(PlPassportNumberProvider.class);
	}
}
