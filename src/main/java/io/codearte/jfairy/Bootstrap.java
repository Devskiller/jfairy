package io.codearte.jfairy;

import com.google.common.base.Optional;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.data.DataMasterModule;
import io.codearte.jfairy.data.MapBasedDataMaster;
import io.codearte.jfairy.producer.util.LanguageCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Locale;
import java.util.Random;

/**
 * <p>Using a {@link #builder()}, you can configure the following fields:</p>
 * <ul>
 * <li><tt>locale</tt>: Specifies the locale for the random data file.</li>
 * <li><tt>filePrefix</tt>: Specifies the file prefix.
 * (So if you specify "jfairy" here and English for Locale, the data file will be
 * "jfairy_en.yml" under the classpath.)
 * </li>
 * <li><tt>random</tt>: The Random object to use.</li>
 * <li><tt>randomSeed</tt>: A specific random seed to use. Use this if you want the resulting
 * data to be <strong>deterministic</strong> based on it, such as if you want the same test
 * ID in a database to always result in the same fake name.
 * </li>
 * </ul>
 * Obviously, don't set both <tt>random</tt> and <tt>randomSeed</tt>, only the last one you set will
 * actually take effect.
 *
 * @author Jakub Kubrynski
 * @author Olga Maciaszek-Sharma
 */
public class Bootstrap {

	private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

	private static final String DATA_FILE_PREFIX = "jfairy";

	public static Fairy createFairy(DataMaster dataMaster, Locale locale, Random random) {



		FairyModule fairyModule = getFairyModuleForLocale(dataMaster, locale, random);

		Injector injector = Guice.createInjector(fairyModule);

		FairyFactory fairyFactory = injector.getInstance(FairyFactory.class);

		return fairyFactory.createFairy();
	}


	private static void fillDefaultDataMaster(MapBasedDataMaster dataMaster, Locale locale, String filePrefix) {
		try {
			dataMaster.readResources(filePrefix + ".yml");
			dataMaster.readResources(filePrefix + "_" + locale.getLanguage() + ".yml");
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Creates a Builder that will let you configure a Fairy's fields one by one.
	 *
	 * @return a Builder instance.
	 */
	public static Builder builder() {
		return new Builder();
	}


	/**
	 * Use this factory method to create dataset containing default jfairy.yml and jfairy_{langCode}.yml files
	 * merged with custom files with the same name
	 *
	 * @return Fairy instance
	 */
	public static Fairy create() {
		return builder().build();
	}

	/**
	 * Use this factory method to create dataset containing default jfairy.yml and jfairy_{langCode}.yml files
	 * merged with custom files with the same name
	 *
	 * @param locale will be used to assess langCode for data file
	 * @return Fairy instance
	 */
	public static Fairy create(Locale locale) {
		return builder().withLocale(locale).build();
	}

	/**
	 * Use this factory method to create your own dataset overriding bundled one
	 *
	 * @param locale         will be used to assess langCode for data file
	 * @param dataFilePrefix prefix of the data file - final pattern will be jfairy.yml and dataFilePrefix_{langCode}.yml
	 * @return Fairy instance
	 */
	public static Fairy create(Locale locale, String dataFilePrefix) {
		return builder().withLocale(locale)
				.withFilePrefix(dataFilePrefix)
				.build();
	}


	public static Fairy create(Provider<DataMaster> dataMaster, Locale locale) {
		return builder().withDataMasterProvider(dataMaster).withLocale(locale).build();
	}

	private static FairyModule getFairyModuleForLocale(DataMaster dataMaster, Locale locale, Random random) {
		LanguageCode code = LanguageCode.valueOf(locale.getLanguage().toUpperCase());
		switch (code) {
			case PL:
				return new PlFairyModule(dataMaster, random);
			case EN:
				return new EnFairyModule(dataMaster, random);
			case ES:
				return new EsFairyModule(dataMaster, random);
			default:
				LOG.info("No data for your language - using EN");
				return new EnFairyModule(dataMaster, random);
		}
	}

	public static class Builder {

		private Locale locale = Locale.ENGLISH;
		private String filePrefix = DATA_FILE_PREFIX;
		private Random random = new Random();
		private DataMaster dataMaster;


		private MapBasedDataMaster getDefaultDataMaster() {
			Injector injector = Guice.createInjector(new DataMasterModule(random));
			return injector.getInstance(MapBasedDataMaster.class);
		}

		private Builder() {

		}

		/**
		 * Sets the locale for the resulting Fairy.
		 *
		 * @param locale The Locale to set.
		 * @return the same Builder (for chaining).
		 */
		public Builder withLocale(Locale locale) {
			this.locale = locale;
			return this;
		}

		/**
		 * Sets the data file prefix for the resulting Fairy.
		 *
		 * @param filePrefix The prefix of the file (such as "jfairy" for "jfairy_en.yml").
		 * @return the same Builder (for chaining).
		 */
		public Builder withFilePrefix(String filePrefix) {
			this.filePrefix = filePrefix;
			return this;
		}

		/**
		 * Sets the Random object to use to pick things randomly.
		 *
		 * @param random The Random to use.
		 * @return the same Builder (for chaining).
		 */
		public Builder withRandom(Random random) {
			this.random = random;
			return this;
		}

		/**
		 * Sets the random seed to use to pick things randomly. (If you set this, you will always
		 * get the same result when you generate things.)
		 *
		 * @param randomSeed The random seed to use.
		 * @return the same Builder (for chaining).
		 */
		public Builder withRandomSeed(long randomSeed) {
			this.random = new Random(randomSeed);
			return this;
		}

		/**
		 * Sets a custom DataMaster implementation.
		 *
		 * @param dataMasterProvider The random seed to use.
		 * @return the same Builder (for chaining).
		 */
		public Builder withDataMasterProvider(Provider<DataMaster> dataMasterProvider) {
			this.dataMaster = dataMasterProvider.get();
			return this;
		}


		/**
		 * Returns the completed Fairy.
		 *
		 * @return Fairy instance
		 */
		public Fairy build() {
			if (dataMaster == null) {
				dataMaster = getDefaultDataMaster();
				fillDefaultDataMaster((MapBasedDataMaster) dataMaster, locale, filePrefix);
			}
			return createFairy(dataMaster, locale, random);
		}
	}


}
