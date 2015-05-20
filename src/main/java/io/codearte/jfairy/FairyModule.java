package io.codearte.jfairy;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.dataProvider.company.CompanyFactory;
import io.codearte.jfairy.dataProvider.payment.IBANFactory;
import io.codearte.jfairy.dataProvider.person.PersonFactory;
import io.codearte.jfairy.dataProvider.person.locale.pl.PeselFactory;

import java.util.Random;

/**
 * @author jkubrynski@gmail.com
 * @author Olga Maciaszek-Sharma
 * @since 2013-11-15
 */
public abstract class FairyModule extends AbstractModule {

	private final Random random;

	private final DataMaster dataMaster;

	public FairyModule(DataMaster dataMaster, Random random) {
		this.random = random;
		this.dataMaster = dataMaster;
	}

	@Override
	protected void configure() {
		bind(DataMaster.class).toInstance(dataMaster);
		bind(Random.class).toInstance(random);

		install(new FactoryModuleBuilder().build(PersonFactory.class));
		install(new FactoryModuleBuilder().build(FairyFactory.class));
		install(new FactoryModuleBuilder().build(CompanyFactory.class));
		install(new FactoryModuleBuilder().build(PeselFactory.class));
		install(new FactoryModuleBuilder().build(IBANFactory.class));
	}
}
