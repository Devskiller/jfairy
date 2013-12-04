/*
 * Copyright (c) 2013 Codearte
 */
package org.jfairy;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jfairy.data.DataMaster;
import org.jfairy.producer.BaseProducer;
import org.jfairy.producer.DateProducer;
import org.jfairy.producer.company.Company;
import org.jfairy.producer.net.Network;
import org.jfairy.producer.payment.CreditCardProducer;
import org.jfairy.producer.person.Person;
import org.jfairy.producer.person.PersonProperties;
import org.jfairy.producer.text.Text;

import java.io.IOException;
import java.util.Locale;

/**
 * Entry class
 */
public final class Fairy {

	private static final String DATA_FILE_PREFIX = "fairyland";

	private final Injector injector;

	private Fairy(Locale locale, String filePrefix) {
		injector = Guice.createInjector(new FairyModule());

		try {
			DataMaster dataMaster = injector.getInstance(DataMaster.class);
			dataMaster.readResources(filePrefix + ".yml");
			dataMaster.readResources(filePrefix + "_" + locale.getLanguage() + ".yml");
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}

	/**
	 * Use this factory method to create dataset containing default fairyland.yml and fairyland_{langCode}.yml files
	 * merged with custom files with the same name
	 *
	 * @return Fairy instance
	 */
	public static Fairy create() {
		return create(Locale.ENGLISH);
	}

	/**
	 * Use this factory method to create dataset containing default fairyland.yml and fairyland_{langCode}.yml files
	 * merged with custom files with the same name
	 *
	 * @param locale will be used to assess langCode for data file
	 * @return Fairy instance
	 */
	public static Fairy create(Locale locale) {
		return create(locale, DATA_FILE_PREFIX);
	}

	/**
	 * Use this factory method to create your own dataset overriding bundled one
	 *
	 * @param locale         will be used to assess langCode for data file
	 * @param dataFilePrefix prefix of the data file - final pattern will be fairyland.yml and dataFilePrefix_{langCode}.yml
	 * @return Fairy instance
	 */
	public static Fairy create(Locale locale, String dataFilePrefix) {
		return new Fairy(locale, dataFilePrefix);
	}

	/**
	 * Use this method for generating texts
	 * @return A {@link org.jfairy.producer.text.Text} instance
	 */
	public Text text() {
		return injector.getInstance(Text.class);
	}

	/**
	 * Use this method for fake persons
	 * @param personProperties desired person features
	 * @return A {@link org.jfairy.producer.person.Person} instance
	 */
	public Person person(PersonProperties.PersonProperty... personProperties) {
		Person person = injector.getInstance(Person.class);

		for (PersonProperties.PersonProperty personProperty : personProperties) {
			injector.injectMembers(personProperty);
			personProperty.apply(person);
		}

		person.generate();
		return person;
	}

	/**
	 * Use this method to generate fake company
	 * @return A {@link org.jfairy.producer.company.Company} instance
	 */
	public Company company() {
		return injector.getInstance(Company.class);
	}

	/**
	 * Use this method for get standard tools
	 * @return A {@link org.jfairy.producer.BaseProducer} instance
	 */
	public BaseProducer baseProducer() {
		return injector.getInstance(BaseProducer.class);
	}

  public DateProducer dateProducer() {
    return injector.getInstance(DateProducer.class);
  }

	public CreditCardProducer creditCard() {
		return injector.getInstance(CreditCardProducer.class);
	}

  public Network network() {
    return injector.getInstance(Network.class);
  }
}
