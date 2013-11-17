/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer.util;

import eu.codearte.fairyland.producer.BaseProducer;
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
	private final BaseProducer baseProducer;
	private final DateGenerator dateGenerator;

	@Inject
	public RandomDataGenerator(DataMaster data, BaseProducer baseProducer, DateGenerator dateGenerator) {
		this.data = data;
		this.baseProducer = baseProducer;
		this.dateGenerator = dateGenerator;
	}

	public DateTime randomDateInThePast(int years) {
		return dateGenerator.randomDateInThePast(years);
	}

	public List<String> randomElements(List<String> elements, int count) {
		return baseProducer.randomElements(elements, count);
	}

	public String getValue(String data) {
		return this.data.getString(data);
	}

	public String getValuesOfType(String dataKey, final String type) {
		LOG.trace("getValuesOfType(dataKey={}, type={})", dataKey, type);

		Map<String, List<String>> stringMap = data.getStringMap(dataKey);

		List<String> entries = stringMap.get(type.toLowerCase());

		LOG.trace("Selected entries {}", entries);
		return baseProducer.randomElement(entries);
	}

	public String getValues(String key) {
		return baseProducer.randomElement(data.getStringList(key));
	}
}
