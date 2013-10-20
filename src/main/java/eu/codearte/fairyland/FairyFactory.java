/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.Men;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.person.Women;
import eu.codearte.fairyland.producer.text.FairUtil;
import eu.codearte.fairyland.producer.text.Text;
import eu.codearte.fairyland.producer.text.TextProducer;
import eu.codearte.fairyland.producer.util.CalendarGenerator;
import eu.codearte.fairyland.producer.util.TimeProvider;

class FairyFactory {

  private static final int SEED = 1761283695;

  public static RandomDataGenerator createRandomDataGenerator(DataMaster dataMaster,
                                                              RandomGenerator randomGenerator,
                                                              CalendarGenerator randomCalendar) {
    return new RandomDataGenerator(dataMaster, randomGenerator, randomCalendar);
  }

  public static RandomGenerator createRandomGenerator() {
    return new RandomGenerator(SEED);
  }

  public static Text createText(DataMaster dataMaster,
                                RandomGenerator randomGenerator,
                                FairUtil fairUtil,
                                CalendarGenerator calendarGenerator) {
    return new Text(
        createTextProducer(dataMaster, randomGenerator, fairUtil, calendarGenerator),
        randomGenerator);
  }

  public static FairUtil createStringifyUtil(RandomGenerator randomGenerator, TimeProvider timeProvider) {
    return createStringifyUtil1(randomGenerator, timeProvider);
  }

  private static TextProducer createTextProducer(DataMaster dataMaster,
                                                 RandomGenerator randomGenerator,
                                                 FairUtil fairUtil,
                                                 CalendarGenerator calendarGenerator) {
    return new TextProducer(
        createRandomDataGenerator(dataMaster, randomGenerator, calendarGenerator),
        randomGenerator,
        fairUtil);
  }

  private static FairUtil createStringifyUtil1(RandomGenerator randomGenerator, TimeProvider dateProvider) {
    return new FairUtil(randomGenerator, dateProvider);
  }

  public static Person createPerson(DataMaster dataMaster,
                                    RandomGenerator randomGenerator,
                                    FairUtil fairUtil,
                                    CalendarGenerator calendarGenerator) {
    return new Person(
        createRandomDataGenerator(dataMaster, randomGenerator, calendarGenerator),
        randomGenerator,
        fairUtil);
  }

  public static Women createWomen(DataMaster dataMaster,
                                  RandomGenerator randomGenerator,
                                  FairUtil fairUtil,
                                  CalendarGenerator calendarGenerator) {
    return new Women(
        createRandomDataGenerator(dataMaster, randomGenerator, calendarGenerator),
        randomGenerator,
        fairUtil);
  }

  public static Men createMen(DataMaster dataMaster,
                              RandomGenerator randomGenerator,
                              FairUtil fairUtil,
                              CalendarGenerator calendarGenerator) {
    return new Men(
        createRandomDataGenerator(dataMaster, randomGenerator, calendarGenerator),
        randomGenerator,
        fairUtil);
  }

  public static Company createCompany(DataMaster dataMaster,
                                      RandomGenerator randomGenerator,
                                      FairUtil fairUtil,
                                      CalendarGenerator calendarGenerator) {
    return new Company(
        createRandomDataGenerator(dataMaster, randomGenerator, calendarGenerator),
        randomGenerator,
        fairUtil);
  }
}
