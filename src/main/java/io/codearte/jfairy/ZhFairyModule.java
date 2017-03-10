package io.codearte.jfairy;

import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;
import io.codearte.jfairy.producer.company.CompanyFactory;
import io.codearte.jfairy.producer.company.CompanyProvider;
import io.codearte.jfairy.producer.company.DefaultCompanyProvider;
import io.codearte.jfairy.producer.company.locale.zh.ZhCompanyProvider;
import io.codearte.jfairy.producer.company.locale.zh.ZhVATIdentificationNumberProvider;
import io.codearte.jfairy.producer.payment.DefaultIBANProvider;
import io.codearte.jfairy.producer.payment.IBANFactory;
import io.codearte.jfairy.producer.payment.IBANProvider;
import io.codearte.jfairy.producer.person.*;
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
		bind(DataMaster.class).toInstance(dataMaster);
		bind(Random.class).toInstance(random);

		install(new FactoryModuleBuilder().build(FairyFactory.class));
		install(new FactoryModuleBuilder().implement(PersonProvider.class, DefaultPersonProvider.class).build(PersonFactory.class));
		install(new FactoryModuleBuilder().implement(CompanyProvider.class, ZhCompanyProvider.class).build(CompanyFactory.class));
		install(new FactoryModuleBuilder().implement(IBANProvider.class, DefaultIBANProvider.class).build(IBANFactory.class));

		// Social Insurance Number is the same as ID number in China now
		bind(NationalIdentificationNumberFactory.class).to(NoNationalIdentificationNumberFactory.class);
		bind(NationalIdentityCardNumberProvider.class).to(ZhNationalIdentityCardNumberProvider.class);
		bind(VATIdentificationNumberProvider.class).to(ZhVATIdentificationNumberProvider.class);
		bind(AddressProvider.class).to(ZhAddressProvider.class);
		bind(PassportNumberProvider.class).to(ZhPassportNumberProvider.class);
	}
}
