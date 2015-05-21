/*
 * Copyright (c) 2013 Codearte
 */
package io.codearte.jfairy;


import com.google.inject.Provider;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.dataProvider.BaseProducer;
import io.codearte.jfairy.dataProvider.DateProducer;
import io.codearte.jfairy.dataProvider.company.Company;
import io.codearte.jfairy.dataProvider.company.CompanyFactory;
import io.codearte.jfairy.dataProvider.net.NetworkProducer;
import io.codearte.jfairy.dataProvider.payment.CreditCard;
import io.codearte.jfairy.dataProvider.person.Person;
import io.codearte.jfairy.dataProvider.person.PersonFactory;
import io.codearte.jfairy.dataProvider.person.PersonProperties;
import io.codearte.jfairy.dataProvider.text.TextProducer;

import javax.inject.Inject;
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

	public static Fairy create(DataMaster dataMaster) {
		return Bootstrap.create(dataMaster);
	}

	public static Fairy create(DataMaster dataMaster, Locale locale) {
		return Bootstrap.create(dataMaster, locale);
	}


	public static Bootstrap.Builder builder() {
		return Bootstrap.builder();
	}

	/**
	 * Use this method for generating texts
	 *
	 * @return A {@link io.codearte.jfairy.dataProvider.text.TextProducer} instance
	 */
	public TextProducer textProducer() {
		return textProducer;
	}

	/**
	 * Use this method for fake persons
	 *
	 * @param personProperties desired person features
	 * @return A {@link io.codearte.jfairy.dataProvider.person.Person} instance
	 */
	public Person person(PersonProperties.PersonProperty... personProperties) {
		return personFactory.producePersonProvider(personProperties).get();
	}

	/**
	 * Use this method to generate fake company
	 *
	 * @return A {@link io.codearte.jfairy.dataProvider.company.CompanyProvider} instance
	 */
	public Company company() {
		return companyFactory.produceCompany().get();
	}

	/**
	 * Use this method for get standard tools
	 *
	 * @return A {@link io.codearte.jfairy.dataProvider.BaseProducer} instance
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
