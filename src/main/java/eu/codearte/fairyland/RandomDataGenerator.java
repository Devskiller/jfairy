/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.RandomProducer;
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
	private final RandomProducer randomProducer;

	@Inject
	public RandomDataGenerator(DataMaster data, RandomProducer randomProducer) {
		this.data = data;
		this.randomProducer = randomProducer;
	}

	public String getValue(String data) {
		return this.data.getString(data);
	}

	public String getValuesOfType(String dataKey, final String type) {
		LOG.trace("getValuesOfType(dataKey={}, type={})", dataKey, type);

		Map<String, List<String>> stringMap = data.getStringMap(dataKey);

		List<String> entries = stringMap.get(type.toLowerCase());

		LOG.trace("Selected entries {}", entries);
		return randomProducer.randomElement(entries);
	}

	public String getValues(String key) {
		return randomProducer.randomElement(data.getStringList(key));
	}

}
