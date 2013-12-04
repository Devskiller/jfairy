package org.jfairy;

import com.google.inject.AbstractModule;
import org.jfairy.producer.VATIdentificationNumber;
import org.jfairy.producer.locale.pl.NIP;
import org.jfairy.producer.person.NationalIdentificationNumber;
import org.jfairy.producer.person.NationalIdentityCardNumber;
import org.jfairy.producer.person.locale.pl.Pesel;
import org.jfairy.producer.person.locale.pl.PolishIdentityCardNumber;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-15
 */
class FairyModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(NationalIdentificationNumber.class).to(Pesel.class);
		bind(NationalIdentityCardNumber.class).to(PolishIdentityCardNumber.class);
		bind(VATIdentificationNumber.class).to(NIP.class);
	}
}
