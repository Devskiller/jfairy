package io.codearte.jfairy;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.producer.company.CompanyFactory;
import io.codearte.jfairy.producer.payment.IBANFactory;
import io.codearte.jfairy.producer.person.PersonFactory;
import io.codearte.jfairy.producer.person.locale.pl.PeselFactory;

import java.util.Random;

/**
 * @author jkubrynski@gmail.com
 * @author Olga Maciaszek-Sharma
 * @since 2013-11-15
 */
public abstract class FairyModule extends AbstractModule {

	private final Random random;

	private final Class<DataMaster> dataMasterClass;

	public FairyModule(Class<DataMaster> dataMasterClass, Random random) {
		this.random = random;
		this.dataMasterClass = dataMasterClass;
	}

	@Override
	protected void configure() {
		bind(DataMaster.class).to(dataMasterClass);
		bind(Random.class).toInstance(random);

		install(new FactoryModuleBuilder().build(PersonFactory.class));
		install(new FactoryModuleBuilder().build(FairyFactory.class));
		install(new FactoryModuleBuilder().build(CompanyFactory.class));
		install(new FactoryModuleBuilder().build(PeselFactory.class));
		install(new FactoryModuleBuilder().build(IBANFactory.class));
	}
}
