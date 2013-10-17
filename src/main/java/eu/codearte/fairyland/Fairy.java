/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.*;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.text.Text;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Locale;

import static eu.codearte.fairyland.FairyFactory.*;

/**
 * Entry class
 */
public class Fairy {

  private static final String DATA_FILE_PREFIX = "fairyland_";

  private DataMaster dataMaster;
  private RandomGenerator randomGenerator = createRandomGenerator();

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
      Constructor<T> constructor = producer.getConstructor(RandomDataGenerator.class, RandomGenerator.class);
      RandomDataGenerator randomDataGenerator = createRandomDataGenerator(dataMaster, randomGenerator);
      return constructor.newInstance(randomDataGenerator, randomGenerator);
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Text text() {
    return createText(dataMaster, randomGenerator);
  }

  public Person person() {
    return createPerson(dataMaster, randomGenerator).generate();
  }

  public Company company() {
    return createCompany(dataMaster, randomGenerator);
  }
}
