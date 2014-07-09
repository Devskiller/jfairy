/*
 * Copyright (c) 2013 Codearte
 */
package org.jfairy;

import com.google.inject.Inject;
import org.jfairy.producer.BaseProducer;
import org.jfairy.producer.DateProducer;
import org.jfairy.producer.company.Company;
import org.jfairy.producer.company.CompanyFactory;
import org.jfairy.producer.company.CompanyProvider;
import org.jfairy.producer.net.NetworkProducer;
import org.jfairy.producer.payment.CreditCard;
import org.jfairy.producer.person.Person;
import org.jfairy.producer.person.PersonFactory;
import org.jfairy.producer.person.PersonProperties;
import org.jfairy.producer.text.TextProducer;

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
	 * @return A {@link org.jfairy.producer.text.TextProducer} instance
	 */
	public TextProducer textProducer() {
		return textProducer;
	}

	/**
	 * Use this method for fake persons
	 *
	 * @param personProperties desired person features
	 * @return A {@link org.jfairy.producer.person.Person} instance
	 */
	public Person person(PersonProperties.PersonProperty... personProperties) {
		return personFactory.producePerson(personProperties).get();
	}

	/**
	 * Use this method to generate fake company
	 *
	 * @return A {@link org.jfairy.producer.company.CompanyProvider} instance
	 */
	public Company company() {
		return companyFactory.produceCompany().get();
	}

	/**
	 * Use this method for get standard tools
	 *
	 * @return A {@link org.jfairy.producer.BaseProducer} instance
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
