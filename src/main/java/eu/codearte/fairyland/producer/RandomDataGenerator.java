/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import eu.codearte.fairyland.DataMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

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
    LOG.debug("getValuesOfType(dataKey={}, type={})", dataKey, type);
    Map<String, String> stringMap = data.getStringMap(dataKey);

    ImmutableList<String> entries = from(stringMap.entrySet())
        .filter(ofType(type))
        .transform(toKeys())
        .toList();
    LOG.debug("Selected entries {}", entries);
    return random.randomElement(entries);
  }

  private Predicate<Map.Entry<String, String>> ofType(final String type) {
    return new Predicate<Map.Entry<String, String>>() {
      @Override
      public boolean apply(Map.Entry<String, String> input) {
        LOG.debug("Matching '{}' with '{}'", input.getValue(), type);
        return input.getValue().equals(type);
      }
    };
  }

  private Function<Map.Entry<String, String>, String> toKeys() {
    return new Function<Map.Entry<String, String>, String>() {
      @Override
      public String apply(Map.Entry<String, String> input) {
        return input.getKey();
      }
    };
  }

  public String getValues(String key) {
    return random.randomElement(data.getStringList(key));
  }
}
