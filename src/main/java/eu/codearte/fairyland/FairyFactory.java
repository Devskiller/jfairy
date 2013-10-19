/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.*;
import eu.codearte.fairyland.producer.text.StringifyUtil;
import eu.codearte.fairyland.producer.text.Text;

public class FairyFactory {

  private static final int SEED = 1761283695;

  public static RandomDataGenerator createRandomDataGenerator(DataMaster dataMaster, RandomGenerator randomGenerator) {
    return new RandomDataGenerator(dataMaster, randomGenerator);
  }

  public static RandomGenerator createRandomGenerator() {
    return new RandomGenerator(SEED);
  }

  public static Text createText(DataMaster dataMaster, RandomGenerator randomGenerator) {
    return new Text(createTextProducer(dataMaster, randomGenerator), randomGenerator,
        createStringifyUtil(randomGenerator));
  }

  private static StringifyUtil createStringifyUtil(RandomGenerator randomGenerator) {
    return new StringifyUtil(randomGenerator);
  }

  private static TextProducer createTextProducer(DataMaster dataMaster, RandomGenerator randomGenerator) {
    return new TextProducer(createRandomDataGenerator(dataMaster, randomGenerator), randomGenerator);
  }

  public static PersonProducer createPerson(DataMaster dataMaster, RandomGenerator randomGenerator) {
    return new PersonProducer(createRandomDataGenerator(dataMaster, randomGenerator), randomGenerator);
  }

  public static Company createCompany(DataMaster dataMaster, RandomGenerator randomGenerator) {
    return new Company(createRandomDataGenerator(dataMaster, randomGenerator), randomGenerator);
  }
}
