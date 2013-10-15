package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;
import eu.codearte.fairyland.RandomGenerator;

import java.util.List;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public abstract class FairyProducer {

  private final DataMaster dataMaster;
  private final RandomGenerator random;

  public FairyProducer(DataMaster dataMaster) {
    this.dataMaster = dataMaster;
    this.random = new RandomGenerator();
  }

  public RandomGenerator random(){
    return random;
  }

  public String getData(String key) {
    List<String> elements = dataMaster.getData(key);
    if (elements != null) {
      return random.randomElement(elements);
    }
    throw new IllegalArgumentException("No such key: " + key);
  }

}
