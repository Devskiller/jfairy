/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import eu.codearte.fairyland.DataMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.FluentIterable.from;

public class RandomDataGenerator {
  private static final Logger LOG = LoggerFactory.getLogger(RandomDataGenerator.class);
  private final DataMaster data;
  private final RandomGenerator random;

  public RandomDataGenerator(DataMaster data, RandomGenerator random) {
    this.data = data;
    this.random = random;
  }

  public List<String> randomElements(List<String> elements, int count) {
    return random.randomElements(elements, count);
  }

  public String getValue(String data) {
    return this.data.getString(data);
  }

  public String getValuesOfType(String dataKey, final String type) {
    LOG.trace("getValuesOfType(dataKey={}, type={})", dataKey, type);

    Map<String, List<String>> stringMap = data.getStringMap(dataKey);

    List<String> entries = stringMap.get(type);

    LOG.trace("Selected entries {}", entries);
    return random.randomElement(entries);
  }

  public String getValues(String key) {
    return random.randomElement(data.getStringList(key));
  }
}
