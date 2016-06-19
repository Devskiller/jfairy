package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.pl.PlVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.pl.PeselFactory;
import io.codearte.jfairy.producer.person.locale.pl.PlIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.pl.PlPassportNumberProvider;
import io.codearte.jfairy.producer.util.CharConverter;
import io.codearte.jfairy.producer.util.locale.PlCharConverter;

import java.util.Random;

/**
 * @author Olga Maciaszek-Sharma
 * @since 21.02.15
 */
public class PlFairyModule extends FairyModule {

	public PlFairyModule(DataMaster dataMaster, Random random) {
		super(dataMaster, random);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(PeselFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(PlIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(PlVATIdentificationNumberProvider.class);
		bind(PassportNumberProvider.class).to(PlPassportNumberProvider.class);
		bind(CharConverter.class).to(PlCharConverter.class);
	}
}
