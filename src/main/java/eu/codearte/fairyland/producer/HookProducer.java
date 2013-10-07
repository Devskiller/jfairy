package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataHolder;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-07
 */
public abstract class HookProducer {

  protected DataHolder dataHolder;

  public HookProducer(DataHolder dataHolder) {
    this.dataHolder = dataHolder;
  }
}
