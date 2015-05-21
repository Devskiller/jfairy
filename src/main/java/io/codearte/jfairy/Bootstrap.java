package io.codearte.jfairy;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.codearte.jfairy.data.DataMaster;
import io.codearte.jfairy.data.DataMasterModule;
import io.codearte.jfairy.data.MapBasedDataMaster;
import io.codearte.jfairy.data.MapBasedDataMasterFactory;
import io.codearte.jfairy.dataProvider.util.LanguageCode;

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
 * a
 *
 * @author Jakub Kubrynski
 * @author Olga Maciaszek-Sharma
 */
public class Bootstrap {

	private static final String DATA_FILE_PREFIX = "jfairy";

	public static Fairy createFairy(DataMaster dataMaster, Locale locale, String filePrefix, Random random) {

		FairyModule fairyModule = getFairyModuleForLocale(dataMaster, locale, random);

		Injector injector = Guice.createInjector(fairyModule);

		FairyFactory instance = injector.getInstance(FairyFactory.class);

		return instance.createFairy();
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

	public static Fairy create(DataMaster dataMaster) {
		return builder().withDataMaster(dataMaster).build();
	}

	public static Fairy create(DataMaster dataMaster, Locale locale) {
		return builder().withDataMaster(dataMaster).withLocale(locale).build();
	}

	private static FairyModule getFairyModuleForLocale(DataMaster dataMaster, Locale locale, Random random) {
		LanguageCode code = LanguageCode.valueOf(locale.getLanguage().toUpperCase());
		switch (code) {
			case PL:
				return new PlFairyModule(dataMaster, random);
			case EN:
				return new EnFairyModule(dataMaster, random);
			default:
				return new EnFairyModule(dataMaster, random);
		}
	}

	public static class Builder {

		private Locale locale = Locale.ENGLISH;
		private String filePrefix = DATA_FILE_PREFIX;
		private Random random = new Random();
		private DataMaster dataMaster = getDefaultDataMasterFactory().createMapBasedDataMaster();


		private MapBasedDataMasterFactory getDefaultDataMasterFactory() {
			DataMasterModule dataMasterModule = new DataMasterModule();
			Injector injector = Guice.createInjector(dataMasterModule);
			return injector.getInstance(MapBasedDataMasterFactory.class);
		}

		private Builder() {
			fillDefaultDataMaster((MapBasedDataMaster) dataMaster, locale, filePrefix);
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
		 * @param dataMaster The random seed to use.
		 * @return the same Builder (for chaining).
		 */
		public Builder withDataMaster(DataMaster dataMaster) {
			this.dataMaster = dataMaster;
			return this;
		}


		/**
		 * Returns the completed Fairy.
		 */
		public Fairy build() {
			return createFairy(dataMaster, locale, filePrefix, random);
		}
	}


}
