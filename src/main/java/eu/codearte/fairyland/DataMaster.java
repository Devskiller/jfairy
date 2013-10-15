/*
 * Copyright (c) 2013 Codearte
 */

package eu.codearte.fairyland;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Joiner.on;

public class DataMaster {

  public static final String FIRST_NAME = "firstNames";
  public static final String LAST_NAME = "lastNames";
  public static final String PERSONAL_EMAIL = "personalEmails";
  public static final String DOMAIN = "domains";
  public static final String COMPANY_NAME = "companyNames";
  public static final String COMPANY_SUFFIX = "companySuffixes";
  public static final String COMPANY_EMAIL = "companyEmails";

  private Map<String, List<String>> data = new HashMap<>();

  DataMaster() {
  }

  void appendData(DataMaster dataMaster) {
    data.putAll(dataMaster.data);
  }

  /**
   * This method is used by Yaml decoder
   *
   * @param data
   */
  @SuppressWarnings("unused")
  public void setData(Map<String, List<String>> data) {
    this.data = data;
  }

  public List<String> getData(String key) {
    List<String> elements = data.get(key);
    if (elements != null) {
      return elements;
    }
    throw new IllegalArgumentException("No such key: " + key);
  }

  public String getAsOne(String key) {
    return on(", ").join(data.get(key));
  }



}
