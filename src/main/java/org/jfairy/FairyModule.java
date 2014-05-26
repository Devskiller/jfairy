package org.jfairy;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.jfairy.producer.VATIdentificationNumber;
import org.jfairy.producer.locale.pl.NIP;
import org.jfairy.producer.person.NationalIdentificationNumber;
import org.jfairy.producer.person.NationalIdentityCardNumber;
import org.jfairy.producer.person.PersonFactory;
import org.jfairy.producer.person.locale.pl.Pesel;
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
		bind(NationalIdentificationNumber.class).to(Pesel.class);
		bind(NationalIdentityCardNumber.class).to(PolishIdentityCardNumber.class);
		bind(VATIdentificationNumber.class).to(NIP.class);
		install(new FactoryModuleBuilder().build(PersonFactory.class));
	}
}
