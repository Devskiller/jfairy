package com.devskiller.jfairy;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import com.devskiller.jfairy.data.DataMaster;
import com.devskiller.jfairy.producer.RandomGenerator;
import com.devskiller.jfairy.producer.company.CompanyFactory;
import com.devskiller.jfairy.producer.company.CompanyProvider;
import com.devskiller.jfairy.producer.company.DefaultCompanyProvider;
import com.devskiller.jfairy.producer.payment.DefaultIBANProvider;
import com.devskiller.jfairy.producer.payment.IBANFactory;
import com.devskiller.jfairy.producer.payment.IBANProvider;
import com.devskiller.jfairy.producer.person.DefaultPersonProvider;
import com.devskiller.jfairy.producer.person.PersonFactory;
import com.devskiller.jfairy.producer.person.PersonProvider;

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
