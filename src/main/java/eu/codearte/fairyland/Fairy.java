/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.FairyProducer;
import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.Men;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.person.Women;
import eu.codearte.fairyland.producer.text.StringifyUtil;
import eu.codearte.fairyland.producer.text.Text;
import eu.codearte.fairyland.producer.util.DateProvider;
import eu.codearte.fairyland.producer.util.RandomCalendar;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.Locale;

import static eu.codearte.fairyland.FairyFactory.*;

/**
 * Entry class
 */
public class Fairy {

  private static final String DATA_FILE_PREFIX = "fairyland_";

  private DataMaster dataMaster;
  private final RandomGenerator randomGenerator = createRandomGenerator();
  private final StringifyUtil stringifyUtil = createStringifyUtil(randomGenerator);
  private final DateProvider dateProvider = new DateProvider();
  private final RandomCalendar randomCalendar = new RandomCalendar(randomGenerator, dateProvider);

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

  public <T extends FairyProducer> T produce(Class<T> producer) {
    if (producer == null) {

    }
    try {
      Constructor<T> constructor = producer.getConstructor(RandomDataGenerator.class, RandomGenerator.class,
          StringifyUtil.class);
      return constructor.newInstance(
          createRandomDataGenerator(dataMaster, randomGenerator),
          randomGenerator,
          stringifyUtil);
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Text text() {
    return createText(dataMaster, randomGenerator, stringifyUtil);
  }

  public Person person() {
    return createPerson(dataMaster, randomGenerator, stringifyUtil);
  }

  public Women women() {
    return createWomen(dataMaster, randomGenerator, stringifyUtil);
  }

  public Men men() {
    return createMen(dataMaster, randomGenerator, stringifyUtil);
  }

  public Company company() {
    return createCompany(dataMaster, randomGenerator, stringifyUtil);
  }

  public String numerify(String numberString) {
    return stringifyUtil.numerify(numberString);
  }

  public String letterify(String letterString) {
    return stringifyUtil.letterify(letterString);
  }

  public String bothify(String string) {
    return stringifyUtil.bothify(string);
  }
  
  public Date randomDateInThePast(){
    return randomCalendar.randomDateInThePast();
  }
}
