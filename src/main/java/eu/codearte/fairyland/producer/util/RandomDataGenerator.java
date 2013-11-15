/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util;

import eu.codearte.fairyland.DataMaster;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

@Singleton
public class RandomDataGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(RandomDataGenerator.class);
	private final DataMaster data;
	private final RandomGenerator random;
	private final DateGenerator dateGenerator;

	@Inject
	public RandomDataGenerator(DataMaster data, RandomGenerator random, DateGenerator dateGenerator) {
		this.data = data;
		this.random = random;
		this.dateGenerator = dateGenerator;
	}

	public DateTime randomDateInThePast(int years) {
		return dateGenerator.randomDateInThePast(years);
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
