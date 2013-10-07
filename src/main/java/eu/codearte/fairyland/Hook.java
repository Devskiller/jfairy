package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.HookProducer;

import java.util.Locale;

/**
 * Entry class
 * @author Codearte
 * @since 2013-10-07
 */
public class Hook {

  private Hook(Locale locale) {

  }

  public static Hook director() {
    return director(Locale.ENGLISH);
  }

  public static Hook director(Locale locale) {
    return new Hook(locale);
  }

  public <T extends HookProducer> T produce(Class<T> producer) {
    try {
      return producer.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalArgumentException(e);
    }
  }

}
