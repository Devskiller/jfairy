package eu.codearte.fairyland.producer;

import eu.codearte.fairyland.DataMaster;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Codearte
 * @since 2013-10-07
 */
public class Company extends FairyProducer {

  private String name;
  private final String domain;
  private final String email;

  public Company(DataMaster dataMaster) {
    super(dataMaster);
    name = generator.getData(DataMaster.COMPANY_NAME);
    if (generator.trueOrFalse()) {
      name += " " + dataMaster.getStringList(DataMaster.COMPANY_SUFFIX);
    }
    domain = StringUtils.strip(StringUtils.deleteWhitespace(name.toLowerCase()), ".")
        + "." + dataMaster.getStringList(DataMaster.DOMAIN);
    email = generator.getData(DataMaster.COMPANY_EMAIL);
  }

  public String name() {
    return name;
  }

  public String url() {
    return "http://www." + domain;
  }

  public String email() {
    return email + "@" + domain;
  }

}
