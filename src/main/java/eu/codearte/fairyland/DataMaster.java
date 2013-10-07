package eu.codearte.fairyland;

import java.util.*;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public class DataMaster {

  public static final String FIRST_NAME = "firstNames";
  public static final String LAST_NAME = "lastNames";
  public static final String PERSONAL_EMAIL = "personalEmails";
  public static final String DOMAIN = "domains";
  public static final String COMPANY_NAME = "companyNames";
  public static final String COMPANY_SUFFIX = "companySuffixes";
  public static final String COMPANY_EMAIL = "companyEmails";

  private static Random random = new Random();

  private Map<String, List<String>> data = new HashMap<>();

  DataMaster() {
  }

  void appendData(DataMaster dataMaster) {
    data.putAll(dataMaster.data);
  }

  /**
   * This method is used by Yaml decoder
   * @param data
   */
  @SuppressWarnings("unused")
  public void setData(Map<String, List<String>> data) {
    this.data = data;
  }

  public String getData(String key) {
    List<String> elements = data.get(key);
    if (elements != null) {
      return elements.get(random.nextInt(elements.size()));
    }
    throw new IllegalArgumentException("No such key: " + key);
  }

  public boolean getBoolean() {
    return random.nextBoolean();
  }

}
