/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.*;
import eu.codearte.fairyland.producer.text.Text;

public class FairyFactory {

  private static final int SEED = 1761283695;

  public static RandomDataGenerator createRandomDataGenerator(DataMaster dataMaster, RandomGenerator randomGenerator) {
    return new RandomDataGenerator(dataMaster, randomGenerator);
  }

  public static RandomGenerator createRandomGenerator() {
    return new RandomGenerator(SEED);
  }

  public static Text createText(DataMaster dataMaster) {
    RandomGenerator randomGenerator = createRandomGenerator();
    RandomDataGenerator randomDataGenerator = createRandomDataGenerator(dataMaster, randomGenerator);
    return new Text(new TextProducer(randomDataGenerator, randomGenerator), randomGenerator);
  }

  public static Person createPerson(DataMaster dataMaster) {
    RandomGenerator randomGenerator = createRandomGenerator();
    RandomDataGenerator randomDataGenerator = createRandomDataGenerator(dataMaster, randomGenerator);
    return new Person(randomDataGenerator, randomGenerator);
  }

  public static Company createCompany(DataMaster dataMaster) {
    RandomGenerator randomGenerator = createRandomGenerator();
    RandomDataGenerator randomDataGenerator = createRandomDataGenerator(dataMaster, randomGenerator);
    return new Company(randomDataGenerator, randomGenerator);
  }
}
