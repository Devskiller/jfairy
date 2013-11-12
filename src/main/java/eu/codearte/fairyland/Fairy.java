/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.util.RandomGenerator;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.person.Sex;
import eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber;
import eu.codearte.fairyland.producer.text.FairUtil;
import eu.codearte.fairyland.producer.text.Text;
import eu.codearte.fairyland.producer.util.DateGenerator;
import eu.codearte.fairyland.producer.util.TimeProvider;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Locale;

import static eu.codearte.fairyland.FairyFactory.*;

/**
 * Entry class
 */
public final class Fairy {

   private static final String DATA_FILE_PREFIX = "fairyland_";
   private static final int SEED = 1761283695;

   private DataMaster dataMaster;
   private final RandomGenerator randomGenerator = new RandomGenerator(SEED);
   private final TimeProvider timeProvider = new TimeProvider();
   private final FairUtil fairUtil = createStringifyUtil(randomGenerator, timeProvider);
   private final DateGenerator dateGenerator = new DateGenerator(randomGenerator, timeProvider);

   private Fairy(Locale locale, String filePrefix) {

      try {
         dataMaster = new DataMaster();
         dataMaster.readResources(filePrefix + locale.getLanguage() + ".yml");
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   /**
    * Use this factory method to create dataset containing default fairyland_{langCode}.yml files
    * merged with custom files with the same name
    *
    * @return Fairy instance
    */
   public static Fairy create() {
      return create(Locale.ENGLISH);
   }

   /**
    * Use this factory method to create dataset containing default fairyland_{langCode}.yml files
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
    * @param dataFilePrefix prefix of the data file - final pattern will be dataFilePrefix_{langCode}.yml
    * @return Fairy instance
    */
   public static Fairy create(Locale locale, String dataFilePrefix) {
      return new Fairy(locale, dataFilePrefix);
   }


   public Text text() {
      return createText(dataMaster, randomGenerator, dateGenerator);
   }

   public Person person() {
      return createPerson(dataMaster, randomGenerator, fairUtil, dateGenerator);
   }

   public Company company() {
      return createCompany(dataMaster, randomGenerator, dateGenerator);
   }

   public String nationalIdentityNumber() {
      return new PolishIdentityCardNumber(randomGenerator).generate(
            dateGenerator.randomDateBetweenYears(2000, timeProvider.getCurrentYear()));
   }

   public String nationalIdentificationNumber() {
      return createNationalIdentificationNumber(randomGenerator)
            .generate(dateGenerator.randomDateInThePast(10),
                  randomGenerator.trueOrFalse() ? Sex.male : Sex.female);
   }

   public String numerify(String numberString) {
      return fairUtil.numerify(numberString);
   }

   public String letterify(String letterString) {
      return fairUtil.letterify(letterString);
   }

   public String bothify(String string) {
      return fairUtil.bothify(string);
   }

   public DateTime randomDateInThePast() {
      return dateGenerator.randomDateInThePast(100);
   }
}
