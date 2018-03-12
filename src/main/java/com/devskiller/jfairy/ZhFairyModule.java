package com.devskiller.jfairy;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.RandomGenerator;
import com.devskiller.jfairy.producer.VATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.company.locale.zh.ZhVATIdentificationNumberProvider;
import com.devskiller.jfairy.producer.person.AddressProvider;
import com.devskiller.jfairy.producer.person.NationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.NationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.PassportNumberProvider;
import com.devskiller.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import com.devskiller.jfairy.producer.person.locale.zh.ZhAddressProvider;
import com.devskiller.jfairy.producer.person.locale.zh.ZhNationalIdentityCardNumberProvider;
import com.devskiller.jfairy.producer.person.locale.zh.ZhPassportNumberProvider;

/**
 * com.devskiller.jfairy.ZhFairyModule
 *
 * @author lhfcws
 * @since 2017/03/01
 */
public class ZhFairyModule extends FairyModule {
	public ZhFairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		super(dataMaster, randomGenerator);
	}

	@Override
	protected void configure() {
		super.configure();

		// Social Insurance Number is the same as ID number in China now
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(ZhNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(ZhVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(ZhAddressProvider.class);
		bind(PassportNumberProvider.class).to(ZhPassportNumberProvider.class);
	}
}
