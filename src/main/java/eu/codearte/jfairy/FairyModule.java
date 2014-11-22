package eu.codearte.jfairy;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import eu.codearte.jfairy.producer.VATIdentificationNumber;
import eu.codearte.jfairy.producer.company.CompanyFactory;
import eu.codearte.jfairy.producer.locale.pl.NIP;
import eu.codearte.jfairy.producer.payment.IBANFactory;
import eu.codearte.jfairy.producer.person.NationalIdentityCardNumber;
import eu.codearte.jfairy.producer.person.PersonFactory;
import eu.codearte.jfairy.producer.person.locale.pl.PeselFactory;
import eu.codearte.jfairy.producer.person.locale.pl.PolishIdentityCardNumber;

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
