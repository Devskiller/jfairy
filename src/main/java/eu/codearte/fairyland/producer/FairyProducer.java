package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;
import eu.codearte.fairyland.RandomDataGenerator;
import eu.codearte.fairyland.RandomGenerator;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public abstract class FairyProducer {

  protected final RandomDataGenerator generator;

  public FairyProducer(DataMaster dataMaster) {
    generator = new RandomDataGenerator(dataMaster, new RandomGenerator());
  }

}
