package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.RandomGenerator;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.es.EsVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.AddressProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.locale.es.EsAddressProvider;
import io.codearte.jfairy.producer.person.locale.es.EsNationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.es.EsPassportNumberProvider;

/**
 * @author graux
 * @since 26.04.15
 */
public class EsFairyModule extends FairyModule {

	public EsFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(EsNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(EsVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(EsAddressProvider.class);
		bind(PassportNumberProvider.class).to(EsPassportNumberProvider.class);
	}
}
