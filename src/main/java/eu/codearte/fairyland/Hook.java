/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.annotations.Alpha;
import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.HookProducer;
import eu.codearte.fairyland.producer.Person;

import java.lang.reflect.Constructor;
import java.util.Locale;

/**
 * Entry class
 *
 */
public class Hook {

  private static final String DATA_FILE_PREFIX = "fairyland_";

  private static ThreadLocal<DataMaster> dataMaster = new ThreadLocal<DataMaster>();

  static DataMaster threadSafely() {
    if (dataMaster.get() == null) {
      dataMaster.set(new DataMaster());
      dataMaster.get().loadData(Locale.ENGLISH, DATA_FILE_PREFIX);
    }
    return dataMaster.get();
  }

  private Hook() {

  }

  public static Hook create() {
    return new Hook();
  }

  public static Hook create(Locale locale) {
    return create(locale, DATA_FILE_PREFIX);
  }

  /**
   * Use this factory method to create your own dataset overriding bundled one
   *
   * @param locale         will be used to assess langCode for data file
   * @param dataFilePrefix prefix of the data file - final pattern will be dataFilePrefix_{langCode}.yml
   * @return
   */
  public static Hook create(Locale locale, String dataFilePrefix) {
    threadSafely().loadData(locale, dataFilePrefix);
    return new Hook();
  }

  public <T extends HookProducer> T produce(Class<T> producer) {
    if (producer == null) {

    }
    try {
      Constructor<T> constructor = producer.getConstructor(DataMaster.class);
      return constructor.newInstance(dataMaster);
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Person person() {
    return new Person(threadSafely());
  }

  @Alpha
  public static Person anyPerson() {
    return create().person();
  }

  public Company company() {
    return new Company(threadSafely());
  }
}
