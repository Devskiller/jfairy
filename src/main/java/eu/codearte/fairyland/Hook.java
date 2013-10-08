/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.HookProducer;
import eu.codearte.fairyland.producer.Person;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Entry class
 *
 * @author Codearte
 * @since 2013-10-07
 */
public class Hook {

  private static final String DATA_FILE_PREFIX = "fairyland_";

  private DataMaster dataMaster;

  private Hook(Locale locale, String filePrefix) {
    try {
      Enumeration<URL> resources =
          getClass().getClassLoader().getResources(filePrefix + locale.getLanguage() + ".yml");
      dataMaster = new DataMaster();
      Yaml yaml = new Yaml();
      while (resources.hasMoreElements()) {
        dataMaster.appendData(yaml.loadAs(resources.nextElement().openStream(), DataMaster.class));
      }
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public static Hook create() {
    return create(Locale.ENGLISH);
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
    return new Hook(locale, dataFilePrefix);
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
    return new Person(dataMaster);
  }

  public Company company() {
    return new Company(dataMaster);
  }
}
