/*
 * Copyright (c) 2013. Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.NationalIdentificationNumber;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.person.pl.NIP;
import eu.codearte.fairyland.producer.person.pl.Pesel;
import eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber;
import eu.codearte.fairyland.producer.person.pl.VATIdentificationNumber;
import eu.codearte.fairyland.producer.text.FairUtil;
import eu.codearte.fairyland.producer.text.Text;
import eu.codearte.fairyland.producer.text.TextProducer;
import eu.codearte.fairyland.producer.util.DateGenerator;
import eu.codearte.fairyland.producer.util.TimeProvider;

final class FairyFactory {

    private FairyFactory() {
    }

    public static RandomDataGenerator createRandomDataGenerator(DataMaster dataMaster,
                                                                RandomGenerator randomGenerator,
                                                                DateGenerator randomCalendar) {
        return new RandomDataGenerator(dataMaster, randomGenerator, randomCalendar);
    }

    public static Text createText(DataMaster dataMaster,
                                  RandomGenerator randomGenerator,
                                  DateGenerator dateGenerator) {
        return new Text(
                createTextProducer(dataMaster, randomGenerator, dateGenerator),
                randomGenerator);
    }

    public static FairUtil createStringifyUtil(RandomGenerator randomGenerator, TimeProvider timeProvider) {
        return createStringifyUtil1(randomGenerator, timeProvider);
    }

    private static TextProducer createTextProducer(DataMaster dataMaster,
                                                   RandomGenerator randomGenerator,
                                                   DateGenerator dateGenerator) {
        return new TextProducer(
                createRandomDataGenerator(dataMaster, randomGenerator, dateGenerator),
                randomGenerator);
    }

    private static FairUtil createStringifyUtil1(RandomGenerator randomGenerator, TimeProvider dateProvider) {
        return new FairUtil(randomGenerator, dateProvider);
    }

    public static Person createPerson(DataMaster dataMaster,
                                      RandomGenerator randomGenerator,
                                      FairUtil fairUtil,
                                      DateGenerator dateGenerator) {
        return new Person(
                randomGenerator,
                createRandomDataGenerator(dataMaster, randomGenerator, dateGenerator),
                fairUtil,
                createNationalIdentificationNumber(randomGenerator),
                createNationalIdentityCardNumber(randomGenerator));
    }

    private static NationalIdentityCardNumber createNationalIdentityCardNumber(RandomGenerator randomGenerator) {
        return new PolishIdentityCardNumber(randomGenerator);
    }

    public static NationalIdentificationNumber createNationalIdentificationNumber(RandomGenerator randomGenerator) {
        return new Pesel(randomGenerator);
    }

    public static Company createCompany(DataMaster dataMaster,
                                        RandomGenerator randomGenerator,
                                        DateGenerator dateGenerator) {
        return new Company(
                randomGenerator,
                createRandomDataGenerator(dataMaster, randomGenerator, dateGenerator),
                createVatIdentificationNumber(randomGenerator));
    }

    private static VATIdentificationNumber createVatIdentificationNumber(RandomGenerator randomGenerator) {
        return new NIP(randomGenerator);
    }
}
