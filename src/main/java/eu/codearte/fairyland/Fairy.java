/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import com.google.inject.Guice;
import com.google.inject.Injector;
import eu.codearte.fairyland.data.DataMaster;
import eu.codearte.fairyland.producer.BaseProducer;
import eu.codearte.fairyland.producer.company.Company;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.person.PersonProperties;
import eu.codearte.fairyland.producer.text.Text;

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

	public Text text() {
		return injector.getInstance(Text.class);
	}

	public Person person(PersonProperties.PersonProperty... personProperties) {
		Person person = injector.getInstance(Person.class);

		for (PersonProperties.PersonProperty personProperty : personProperties) {
			injector.injectMembers(personProperty);
			personProperty.apply(person);
		}

		person.generate();
		return person;
	}

	public Company company() {
		return injector.getInstance(Company.class);
	}

	public BaseProducer baseProducer() {
		return injector.getInstance(BaseProducer.class);
	}

}
