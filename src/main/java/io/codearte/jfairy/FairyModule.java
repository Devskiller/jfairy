package io.codearte.jfairy;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.RandomGenerator;
import io.codearte.jfairy.producer.company.CompanyFactory;
import io.codearte.jfairy.producer.company.CompanyProvider;
import io.codearte.jfairy.producer.company.DefaultCompanyProvider;
import io.codearte.jfairy.producer.payment.DefaultIBANProvider;
import io.codearte.jfairy.producer.payment.IBANFactory;
import io.codearte.jfairy.producer.payment.IBANProvider;
import io.codearte.jfairy.producer.person.DefaultPersonProvider;
import io.codearte.jfairy.producer.person.PersonFactory;
import io.codearte.jfairy.producer.person.PersonProvider;

import java.util.Random;

/**
 * @author jkubrynski@gmail.com
 * @author Olga Maciaszek-Sharma
 * @since 2013-11-15
 */
public abstract class FairyModule extends AbstractModule {

	private final RandomGenerator randomGenerator;
	private final DataMaster dataMaster;

	public FairyModule(DataMaster dataMaster, RandomGenerator randomGenerator) {
		this.dataMaster = dataMaster;
		this.randomGenerator = randomGenerator;
	}

	@Override
	protected void configure() {
		bind(DataMaster.class).toInstance(dataMaster);
		bind(RandomGenerator.class).toInstance(randomGenerator);

		install(new FactoryModuleBuilder().build(FairyFactory.class));
		install(new FactoryModuleBuilder().implement(PersonProvider.class, DefaultPersonProvider.class).build(PersonFactory.class));
		install(new FactoryModuleBuilder().implement(CompanyProvider.class, DefaultCompanyProvider.class).build(CompanyFactory.class));
		install(new FactoryModuleBuilder().implement(IBANProvider.class, DefaultIBANProvider.class).build(IBANFactory.class));
	}
}
