package eu.codearte.fairyland;

import com.google.inject.AbstractModule;
import eu.codearte.fairyland.producer.person.NationalIdentificationNumber;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;
import eu.codearte.fairyland.producer.locale.pl.NIP;
import eu.codearte.fairyland.producer.person.locale.pl.Pesel;
import eu.codearte.fairyland.producer.person.locale.pl.PolishIdentityCardNumber;
import eu.codearte.fairyland.producer.VATIdentificationNumber;

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
