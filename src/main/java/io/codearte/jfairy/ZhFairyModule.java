package io.codearte.jfairy;

import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.locale.zh.ZhVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.person.AddressProvider;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.PassportNumberProvider;
import io.codearte.jfairy.producer.person.locale.NoNationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.locale.zh.ZhAddressProvider;
import io.codearte.jfairy.producer.person.locale.zh.ZhNationalIdentityCardNumberProvider;
import io.codearte.jfairy.producer.person.locale.zh.ZhPassportNumberProvider;

import java.util.Random;

/**
 * io.codearte.jfairy.ZhFairyModule
 *
 * @author lhfcws
 * @since 2017/03/01
 */
public class ZhFairyModule extends FairyModule {
	public ZhFairyModule(DataMaster dataMaster, Random random) {
		super(dataMaster, random);
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
