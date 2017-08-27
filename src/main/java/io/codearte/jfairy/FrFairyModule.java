package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.RandomGenerator;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;

/**
 * @author graux
 * @since 26.04.15
 */
public class FrFairyModule extends FairyModule {

	public FrFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
	}
}
