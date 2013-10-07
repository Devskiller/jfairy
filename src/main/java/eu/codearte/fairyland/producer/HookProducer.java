package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public abstract class HookProducer {

  protected DataMaster dataMaster;

  public HookProducer(DataMaster dataMaster) {
    this.dataMaster = dataMaster;
  }
}
