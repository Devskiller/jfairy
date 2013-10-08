package eu.codearte.fairyland;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public class DataMaster {
  private static final Logger LOG = LoggerFactory.getLogger(DataMaster.class);
  private static final String DATA_FILE_PREFIX = "fairyland_";

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

  public void loadData(Locale locale, String filePrefix) {
    try {
      Enumeration<URL> resources =
          getClass().getClassLoader().getResources(filePrefix + locale.getLanguage() + ".yml");
      Yaml yaml = new Yaml();
      while (resources.hasMoreElements()) {
        appendData(yaml.loadAs(resources.nextElement().openStream(), DataMaster.class));
      }
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  void appendData(DataMaster dataMaster) {

    LOG.debug("appendData {}", this.hashCode());
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
