package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public class Person extends HookProducer {

  private final String firstName;
  private final String lastName;
  private final String email;

  public Person(DataMaster dataMaster) {
    super(dataMaster);
    firstName = dataMaster.getData(DataMaster.FIRST_NAME);
    lastName = dataMaster.getData(DataMaster.LAST_NAME);
    email = generateEmail(dataMaster);
  }

  private String generateEmail(DataMaster dataMaster) {
    String temp = "";
    if (dataMaster.getBoolean()) {
      temp = firstName;
      if (dataMaster.getBoolean()) {
        temp += ".";
      }
    }
    return StringUtils.lowerCase(temp + lastName + "@" + dataMaster.getData(DataMaster.PERSONAL_EMAIL));
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
