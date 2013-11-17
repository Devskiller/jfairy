/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland;

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

	@Inject
	public RandomDataGenerator(DataMaster data) {
		this.data = data;
	}

	public String getValue(String dataname) {
		return data.getString(dataname);
	}

	public List<String> getValuesOfType(String dataKey, final String type) {
		LOG.trace("getValuesOfType(dataKey={}, type={})", dataKey, type);

		Map<String, List<String>> stringMap = data.getStringMap(dataKey);

		return stringMap.get(type.toLowerCase());
	}

	public List<String> getValues(String key) {
		return data.getStringList(key);
	}

}
