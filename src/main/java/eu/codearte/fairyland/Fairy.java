/*
 * Copyright (c) 2013 Codearte
 */
package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.person.NationalIdentificationNumber;
import eu.codearte.fairyland.producer.person.NationalIdentityCardNumber;
import eu.codearte.fairyland.producer.person.Person;
import eu.codearte.fairyland.producer.person.Sex;
import eu.codearte.fairyland.producer.person.pl.NIP;
import eu.codearte.fairyland.producer.person.pl.Pesel;
import eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber;
import eu.codearte.fairyland.producer.person.pl.VATIdentificationNumber;
import eu.codearte.fairyland.producer.text.FairUtil;
import eu.codearte.fairyland.producer.text.Text;
import eu.codearte.fairyland.producer.text.TextProducer;
import eu.codearte.fairyland.producer.util.DateGenerator;
import eu.codearte.fairyland.producer.util.RandomDataGenerator;
import eu.codearte.fairyland.producer.util.RandomGenerator;
import eu.codearte.fairyland.producer.util.TimeProvider;
import org.joda.time.DateTime;
import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;

import java.io.IOException;
import java.util.Locale;

/**
 * Entry class
 */
public final class Fairy {

  private static final String DATA_FILE_PREFIX = "fairyland_";
  private static final int SEED = 1761283695;
  private final MutablePicoContainer pico;

  private DataMaster dataMaster;
  private final RandomGenerator randomGenerator = new RandomGenerator(SEED);

  private Fairy(Locale locale, String filePrefix) {
    pico = new PicoBuilder().withCaching().withConstructorInjection().build();

    try {
      dataMaster = new DataMaster();
      dataMaster.readResources(filePrefix + locale.getLanguage() + ".yml");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    pico.as(Characteristics.CACHE).addComponent(dataMaster);
    pico.addComponent(randomGenerator);
    pico.addComponent(FairUtil.class);
    pico.addComponent(TimeProvider.class);
    pico.addComponent(DateGenerator.class);
    pico.addComponent(RandomDataGenerator.class);
    pico.addComponent(Text.class);
    pico.addComponent(TextProducer.class);
    pico.addComponent(Person.class).as(Characteristics.CACHE);
    pico.addComponent(NationalIdentificationNumber.class, Pesel.class);
    pico.addComponent(NationalIdentityCardNumber.class, PolishIdentityCardNumber.class);
    pico.addComponent(VATIdentificationNumber.class, NIP.class);
    pico.as(Characteristics.NO_CACHE).addComponent(Company.class);
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


  public Text text() {
    return pico.getComponent(Text.class);
  }

  public Person person() {
    return pico.getComponent(Person.class);
  }

  public Company company() {
    return pico.getComponent(Company.class);
  }

  public String nationalIdentityNumber() {
    return pico.getComponent(NationalIdentityCardNumber.class).generate(
        pico.getComponent(DateGenerator.class)
            .randomDateBetweenYears(2000, pico.getComponent(TimeProvider.class).getCurrentYear()));
  }

  public String nationalIdentificationNumber() {
    return pico.getComponent(NationalIdentificationNumber.class)
        .generate(pico.getComponent(DateGenerator.class)
            .randomDateInThePast(10), randomGenerator.trueOrFalse() ? Sex.male : Sex.female);
  }

  public String numerify(String numberString) {
    return pico.getComponent(FairUtil.class).numerify(numberString);
  }

  public String letterify(String letterString) {
    return pico.getComponent(FairUtil.class).letterify(letterString);
  }

  public String bothify(String string) {
    return pico.getComponent(FairUtil.class).bothify(string);
  }

  public DateTime randomDateInThePast() {
    return pico.getComponent(DateGenerator.class).randomDateInThePast(100);
  }
}
