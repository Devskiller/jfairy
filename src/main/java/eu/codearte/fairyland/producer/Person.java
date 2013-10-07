package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataHolder;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-07
 */
public class Person extends HookProducer {

  public Person(DataHolder dataHolder) {
    super(dataHolder);
  }

  public String firstName() {
    return dataHolder.getData(DataHolder.NAMES).get(0);
  }

}
