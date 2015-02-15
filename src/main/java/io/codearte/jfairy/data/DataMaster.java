/*
 * Copyright (c) 2013 Codearte
 */

package io.codearte.jfairy.data;

import io.codearte.jfairy.producer.BaseProducer;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
public class DataMaster {

	private final BaseProducer baseProducer;
	private Map<String, Object> dataSource = new CaseInsensitiveMap();

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
		return getData(key, List.class);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getIntegerValueMap(String key) {
		return (Map<String, Integer>) getData(key, Map.class);
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getStringMap(String key) {
		return (Map<String, List<String>>) getData(key, Map.class);
	}

	public String getValuesOfType(String dataKey, final String type) {
		Map<String, List<String>> stringMap = getStringMap(dataKey);

		List<String> entries = stringMap.get(type);

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
		return getData(key, String.class);
	}

	public String getRandomValue(String key) {
		return baseProducer.randomElement(getStringList(key));
	}

	@SuppressWarnings({"unchecked", "ConstantConditions"}) // checked by checkArgument
	private <T> T getData(String key, Class<T> type) {
		checkArgument(key != null, "key cannot be null");
		checkArgument(type != null, "type cannot be null");

		Object element = dataSource.get(key);
		checkArgument(element != null, "No such key: %s", key);
		checkArgument(type.isAssignableFrom(element.getClass()),
				"Element under desired key has incorrect type - should be %s", type.getSimpleName());

		return (T) element;
	}

	//fixme - should be package-private
	public void readResources(String path) throws IOException {
		Enumeration<URL> resources =
				getClass().getClassLoader().getResources(path);

		if (!resources.hasMoreElements()) {
			throw new IllegalArgumentException(String.format("File %s was not found on classpath", path));
		}

		Yaml yaml = new Yaml();
		while (resources.hasMoreElements()) {
			appendData(yaml.loadAs(resources.nextElement().openStream(), Data.class));
		}
	}

	private void appendData(Data dataMaster) {
		dataSource.putAll(dataMaster.getData());
	}

	public static class Data {

		private Map<String, Object> data;

		/**
		 * This method is used by YAML decoder
		 *
		 * @param data fetched from yaml document
		 */
		@SuppressWarnings("unused")
		public void setData(Map<String, Object> data) {
			this.data = data;
		}

		private Map<String, Object> getData() {
			return data;
		}
	}

	private static class CaseInsensitiveMap extends HashMap<String, Object> {

		@Override
		@SuppressWarnings("unchecked")
		public Object put(String key, Object value) {
			String loweredKey = key.toLowerCase();
			Object valueToInsert = value;

			if (value instanceof Map) {
				valueToInsert = new CaseInsensitiveMap();
				((CaseInsensitiveMap) valueToInsert).putAll((Map<? extends String, ?>) value);
			}

			return super.put(loweredKey, valueToInsert);
		}

		@Override
		public void putAll(Map<? extends String, ?> map) {
			for (Map.Entry<? extends String, ?> entry : map.entrySet()) {
				put(entry.getKey(), entry.getValue());
			}
		}

		@Override
		@SuppressWarnings("unchecked")
		public Object get(Object key) {
			return super.get(((String) key).toLowerCase());
		}
	}
}
