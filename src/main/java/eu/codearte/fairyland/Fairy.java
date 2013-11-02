/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.FairyProducer;
import eu.codearte.fairyland.producer.RandomDataGenerator;
import eu.codearte.fairyland.producer.RandomGenerator;
import eu.codearte.fairyland.producer.person.Men;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.person.Sex;
import eu.codearte.fairyland.producer.person.Women;
import eu.codearte.fairyland.producer.person.pl.Pesel;
import eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber;
import eu.codearte.fairyland.producer.text.FairUtil;
import eu.codearte.fairyland.producer.text.Text;
import eu.codearte.fairyland.producer.util.CalendarGenerator;
import eu.codearte.fairyland.producer.util.TimeProvider;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static eu.codearte.fairyland.FairyFactory.*;

/**
 * Entry class
 */
public class Fairy {

    private static final String DATA_FILE_PREFIX = "fairyland_";

    private DataMaster dataMaster;
    private final RandomGenerator randomGenerator = createRandomGenerator();
    private final TimeProvider timeProvider = new TimeProvider();
    private final FairUtil fairUtil = createStringifyUtil(randomGenerator, timeProvider);
    private final CalendarGenerator calendarGenerator = new CalendarGenerator(randomGenerator, timeProvider);

    private Fairy(Locale locale, String filePrefix) {

        try {
            dataMaster = new DataMaster();
            dataMaster.readResources(filePrefix + locale.getLanguage() + ".yml");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Use this factory method to create dataset containing default fairyland_{langCode}.yml files
     * merged with custom files with the same name
     *
     * @return Fairy instance
     */
    public static Fairy create() {
        return create(Locale.ENGLISH);
    }

    /**
     * Use this factory method to create dataset containing default fairyland_{langCode}.yml files
     * merged with custom files with the same name
     *
     * @param locale will be used to assess langCode for data file
     * @return Fairy instance
     */
    public static Fairy create(Locale locale) {
        return create(locale, DATA_FILE_PREFIX);
    }

    /**
     * Use this factory method to create your own dataset overriding bundled one
     *
     * @param locale         will be used to assess langCode for data file
     * @param dataFilePrefix prefix of the data file - final pattern will be dataFilePrefix_{langCode}.yml
     * @return Fairy instance
     */
    public static Fairy create(Locale locale, String dataFilePrefix) {
        return new Fairy(locale, dataFilePrefix);
    }

    public <T extends FairyProducer> T produce(Class<T> producer) {
        if (producer == null) {

        }
        try {
            Constructor<T> constructor = producer.getConstructor(
                    RandomDataGenerator.class,
                    RandomGenerator.class,
                    FairUtil.class);
            return constructor.newInstance(
                    createRandomDataGenerator(dataMaster, randomGenerator, calendarGenerator),
                    randomGenerator,
                    fairUtil);
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Text text() {
        return createText(dataMaster, randomGenerator, fairUtil, calendarGenerator);
    }

    public Person person() {
        return createPerson(dataMaster, randomGenerator, fairUtil, calendarGenerator);
    }

    public Women women() {
        return createWomen(dataMaster, randomGenerator, fairUtil, calendarGenerator);
    }

    public Men men() {
        return createMen(dataMaster, randomGenerator, fairUtil, calendarGenerator);
    }

    public Company company() {
        return createCompany(dataMaster, randomGenerator, fairUtil, calendarGenerator);
    }

    public String nationalIdentityNumber() {
        return new PolishIdentityCardNumber(randomGenerator).identityNumber(
                calendarGenerator.randomCalendarBetweenYears(2000, timeProvider.getYear()));
    }

    public String nationalIdentificationNumber() {
        return new Pesel(randomGenerator).nationalIdentificationNumber(
                calendarGenerator.randomCalendarBetweenYears(1979, timeProvider.getYear()),
                randomGenerator.trueOrFalse() ? Sex.male : Sex.female);
    }

    public String numerify(String numberString) {
        return fairUtil.numerify(numberString);
    }

    public String letterify(String letterString) {
        return fairUtil.letterify(letterString);
    }

    public String bothify(String string) {
        return fairUtil.bothify(string);
    }

    public Date randomDateInThePast() {
        return calendarGenerator.randomDateInThePast();
    }
}
