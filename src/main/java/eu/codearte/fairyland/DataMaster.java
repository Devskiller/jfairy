/*
 * Copyright (c) 2013 Codearte
 */

package eu.codearte.fairyland;

import eu.codearte.fairyland.producer.BaseProducer;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Maps.newHashMap;

@Singleton
public class DataMaster {

	private final BaseProducer baseProducer;
	private Map<String, List<String>> dataSource = newHashMap();

	@Inject
	DataMaster(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	/**
	 * Returns list (null safe) of elements for desired key from dataSource files
	 *
	 * @param key desired node key
	 * @return list of elements for desired key
	 * @throws IllegalArgumentException if no element for key has been found
	 */
	@SuppressWarnings("unchecked")
	public List<String> getStringList(String key) {
		return (List<String>) getData(key);
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getStringMap(String key) {
		return (Map<String, List<String>>) getData(key);
	}

	public String getValuesOfType(String dataKey, final String type) {
		Map<String, List<String>> stringMap = getStringMap(dataKey);

		List<String> entries = stringMap.get(type.toLowerCase());

		return baseProducer.randomElement(entries);
	}

	/**
	 * Returns element (null safe) for desired key from dataSource files
	 *
	 * @param key desired node key
	 * @return string element for desired key
	 * @throws IllegalArgumentException if no element for key has been found
	 */
	public String getString(String key) {
		return (String) getData(key);
	}

	public String getRandomValue(String key) {
		return baseProducer.randomElement(getStringList(key));
	}

	private Object getData(String key) {
		Object element = dataSource.get(key);
		checkArgument(element != null, "No such key: %s", key);
		return element;
	}

	//fixme - should be package-private
	public void readResources(String path) throws IOException {
		Enumeration<URL> resources =
				getClass().getClassLoader().getResources(path);

		Yaml yaml = new Yaml();
		while (resources.hasMoreElements()) {
			appendData(yaml.loadAs(resources.nextElement().openStream(), Data.class));
		}
	}

	private void appendData(Data dataMaster) {
		dataSource.putAll(dataMaster.getData());
	}

	public static class Data {

		private Map<String, List<String>> data;

		/**
		 * This method is used by YAML decoder
		 *
		 * @param data fetched from yaml document
		 */
		@SuppressWarnings("unused")
		public void setData(Map<String, List<String>> data) {
			this.data = data;
		}

		private Map<String, List<String>> getData() {
			return data;
		}
	}

}
