package io.codearte.jfairy;

import com.google.common.base.Optional;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.data.MapBasedDataMaster;
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

	private final Optional<DataMaster> dataMaster;

	public FairyModule(Optional<DataMaster> dataMaster, Random random) {
		this.random = random;
		this.dataMaster = dataMaster;
	}

	@Override
	protected void configure() {
		if (dataMaster.isPresent()) {
			bind(DataMaster.class).toInstance(dataMaster.get());
		} else {
			bind(DataMaster.class).to(MapBasedDataMaster.class);
		}
		bind(Random.class).toInstance(random);

		install(new FactoryModuleBuilder().build(PersonFactory.class));
		install(new FactoryModuleBuilder().build(FairyFactory.class));
		install(new FactoryModuleBuilder().build(CompanyFactory.class));
		install(new FactoryModuleBuilder().build(PeselFactory.class));
		install(new FactoryModuleBuilder().build(IBANFactory.class));
	}
}
