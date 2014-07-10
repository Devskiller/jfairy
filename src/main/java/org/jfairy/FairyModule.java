package org.jfairy;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.jfairy.producer.VATIdentificationNumber;
import org.jfairy.producer.company.CompanyFactory;
import org.jfairy.producer.locale.pl.NIP;
import org.jfairy.producer.payment.IBANFactory;
import org.jfairy.producer.person.NationalIdentityCardNumber;
import org.jfairy.producer.person.PersonFactory;
import org.jfairy.producer.person.locale.pl.PeselFactory;
import org.jfairy.producer.person.locale.pl.PolishIdentityCardNumber;

import java.util.Random;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-15
 */
class FairyModule extends AbstractModule {

	private final Random random;

	public FairyModule(Random random) {
		this.random = random;
	}

	@Override
	protected void configure() {
		bind(Random.class).toInstance(random);
		bind(NationalIdentityCardNumber.class).to(PolishIdentityCardNumber.class);
		bind(VATIdentificationNumber.class).to(NIP.class);

		install(new FactoryModuleBuilder().build(PersonFactory.class));
		install(new FactoryModuleBuilder().build(FairyFactory.class));
		install(new FactoryModuleBuilder().build(CompanyFactory.class));
		install(new FactoryModuleBuilder().build(PeselFactory.class));
		install(new FactoryModuleBuilder().build(IBANFactory.class));
	}
}
