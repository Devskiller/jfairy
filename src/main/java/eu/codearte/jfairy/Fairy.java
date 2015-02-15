/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.jfairy;

import com.google.inject.Inject;
import eu.codearte.jfairy.producer.BaseProducer;
import eu.codearte.jfairy.producer.DateProducer;
import eu.codearte.jfairy.producer.company.Company;
import eu.codearte.jfairy.producer.company.CompanyFactory;
import eu.codearte.jfairy.producer.net.NetworkProducer;
import eu.codearte.jfairy.producer.payment.CreditCard;
import eu.codearte.jfairy.producer.person.Person;
import eu.codearte.jfairy.producer.person.PersonFactory;
import eu.codearte.jfairy.producer.person.PersonProperties;
import eu.codearte.jfairy.producer.text.TextProducer;

import javax.inject.Provider;
import java.util.Locale;

public final class Fairy {

	private final TextProducer textProducer;
	private final PersonFactory personFactory;
	private final NetworkProducer networkProducer;
	private final BaseProducer baseProducer;
	private final DateProducer dateProducer;
	private final Provider<CreditCard> creditCardProvider;
	private final CompanyFactory companyFactory;

	@Inject
	Fairy(TextProducer textProducer, PersonFactory personFactory, NetworkProducer networkProducer,
	      BaseProducer baseProducer, DateProducer dateProducer, Provider<CreditCard> creditCardProvider, CompanyFactory companyFactory) {
		this.textProducer = textProducer;
		this.personFactory = personFactory;
		this.networkProducer = networkProducer;
		this.baseProducer = baseProducer;
		this.dateProducer = dateProducer;
		this.creditCardProvider = creditCardProvider;
		this.companyFactory = companyFactory;
	}

	public static Fairy create() {
		return Bootstrap.create();
	}

	public static Fairy create(Locale locale) {
		return Bootstrap.create(locale);
	}


	public static Bootstrap.Builder builder() {
		return Bootstrap.builder();
	}

	/**
	 * Use this method for generating texts
	 *
	 * @return A {@link eu.codearte.jfairy.producer.text.TextProducer} instance
	 */
	public TextProducer textProducer() {
		return textProducer;
	}

	/**
	 * Use this method for fake persons
	 *
	 * @param personProperties desired person features
	 * @return A {@link eu.codearte.jfairy.producer.person.Person} instance
	 */
	public Person person(PersonProperties.PersonProperty... personProperties) {
		return personFactory.producePersonProvider(personProperties).get();
	}

	/**
	 * Use this method to generate fake company
	 *
	 * @return A {@link eu.codearte.jfairy.producer.company.CompanyProvider} instance
	 */
	public Company company() {
		return companyFactory.produceCompany().get();
	}

	/**
	 * Use this method for get standard tools
	 *
	 * @return A {@link eu.codearte.jfairy.producer.BaseProducer} instance
	 */
	public BaseProducer baseProducer() {
		return baseProducer;
	}

	public DateProducer dateProducer() {
		return dateProducer;
	}

	public CreditCard creditCard() {
		return creditCardProvider.get();
	}

	public NetworkProducer networkProducer() {
		return networkProducer;
	}
}
