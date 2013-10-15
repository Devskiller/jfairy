package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;

import static org.apache.commons.lang3.StringUtils.lowerCase;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public class Person extends FairyProducer {

  private final String firstName;
  private final String lastName;
  private final String email;

  public Person(DataMaster dataMaster) {
    super(dataMaster);
    firstName = generator.getData(DataMaster.FIRST_NAME);
    lastName = generator.getData(DataMaster.LAST_NAME);
    email = generateEmail(dataMaster);
  }

  private String generateEmail(DataMaster dataMaster) {
    String temp = "";
    if (generator.trueOrFalse()) {
      temp = firstName;
      if (generator.trueOrFalse()) {
        temp += ".";
      }
    }
    return lowerCase(temp + lastName + "@" + dataMaster.getData(DataMaster.PERSONAL_EMAIL));
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  public String fullName() {
    return firstName + " " + lastName;
  }

  public String email() {
    return email;
  }
}
