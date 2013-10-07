package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.HookProducer;
import eu.codearte.fairyland.producer.Person;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
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

  private Hook(Locale locale) {
    try {
      Enumeration<URL> resources =
          getClass().getClassLoader().getResources(DATA_FILE_PREFIX + locale.getLanguage() + ".yml");
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
    return new Hook(locale);
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
