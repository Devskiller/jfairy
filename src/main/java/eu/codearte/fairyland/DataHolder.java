package eu.codearte.fairyland;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-07
 */
public class DataHolder {

  public static final String NAMES = "names";

  private Map<String, List<String>> data = new HashMap<>();

  DataHolder() {
  }

  void appendData(DataHolder dataHolder) {
    data.putAll(dataHolder.data);
  }

  public void setData(Map<String, List<String>> data) {
    this.data = data;
  }

  public List<String> getData(String key) {
    return data.get(key);
  }
}
