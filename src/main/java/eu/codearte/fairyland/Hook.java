package eu.codearte.fairyland;

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

  private DataHolder dataHolder;

  private Hook(Locale locale, String filePrefix) {
    try {
      Enumeration<URL> resources =
          getClass().getClassLoader().getResources(filePrefix + locale.getLanguage() + ".yml");
      dataHolder = new DataHolder();
      Yaml yaml = new Yaml();
      while (resources.hasMoreElements()) {
        dataHolder.appendData(yaml.loadAs(resources.nextElement().openStream(), DataHolder.class));
      }
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public static Hook director() {
    return director(Locale.ENGLISH);
  }

  public static Hook director(Locale locale) {
    return director(locale, DATA_FILE_PREFIX);
  }

  /**
   * Use this factory method to create your own dataset overriding bundled one
   * @param locale will be used to assess langCode for data file
   * @param dataFilePrefix prefix of the data file - final pattern will be dataFilePrefix_{langCode}.yml
   * @return
   */
  public static Hook director(Locale locale, String dataFilePrefix) {
    return new Hook(locale, dataFilePrefix);
  }

  public <T extends HookProducer> T produce(Class<T> producer) {
    if (producer == null) {

    }
    try {
      Constructor<T> constructor = producer.getConstructor(DataHolder.class);
      return constructor.newInstance(dataHolder);
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Person person() {
    return new Person(dataHolder);
  }

}
