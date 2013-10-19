/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.MenProducer;
import eu.codearte.fairyland.producer.person.PersonProducer;
import eu.codearte.fairyland.producer.person.WomenProducer;
import eu.codearte.fairyland.producer.text.StringifyUtil;
import eu.codearte.fairyland.producer.text.Text;
import eu.codearte.fairyland.producer.text.TextProducer;

public class FairyFactory {

  private static final int SEED = 1761283695;

  public static RandomDataGenerator createRandomDataGenerator(DataMaster dataMaster, RandomGenerator randomGenerator) {
    return new RandomDataGenerator(dataMaster, randomGenerator);
  }

  public static RandomGenerator createRandomGenerator() {
    return new RandomGenerator(SEED);
  }

  public static Text createText(DataMaster dataMaster, RandomGenerator randomGenerator, StringifyUtil stringifyUtil) {
    return new Text(
        createTextProducer(dataMaster, randomGenerator, stringifyUtil),
        randomGenerator);
  }

  public static StringifyUtil createStringifyUtil(RandomGenerator randomGenerator) {
    return createStringifyUtil1(randomGenerator);
  }

  private static TextProducer createTextProducer(DataMaster dataMaster, RandomGenerator randomGenerator, StringifyUtil stringifyUtil) {
    return new TextProducer(
        createRandomDataGenerator(dataMaster, randomGenerator),
        randomGenerator,
        stringifyUtil);
  }

  private static StringifyUtil createStringifyUtil1(RandomGenerator randomGenerator) {
    return new StringifyUtil(randomGenerator);
  }

  public static PersonProducer createPerson(DataMaster dataMaster, RandomGenerator randomGenerator, StringifyUtil stringifyUtil) {
    return new PersonProducer(
        createRandomDataGenerator(dataMaster, randomGenerator),
        randomGenerator,
        stringifyUtil);
  }

  public static WomenProducer createWomen(DataMaster dataMaster, RandomGenerator randomGenerator, StringifyUtil stringifyUtil) {
    return new WomenProducer(
        createRandomDataGenerator(dataMaster, randomGenerator),
        randomGenerator,
        stringifyUtil);
  }

  public static MenProducer createMen(DataMaster dataMaster, RandomGenerator randomGenerator, StringifyUtil stringifyUtil) {
    return new MenProducer(
        createRandomDataGenerator(dataMaster, randomGenerator),
        randomGenerator,
        stringifyUtil);
  }

  public static Company createCompany(DataMaster dataMaster, RandomGenerator randomGenerator, StringifyUtil stringifyUtil) {
    return new Company(
        createRandomDataGenerator(dataMaster, randomGenerator),
        randomGenerator,
        stringifyUtil);
  }
}
