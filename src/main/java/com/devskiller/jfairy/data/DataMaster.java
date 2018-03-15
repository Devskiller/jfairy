package com.devskiller.jfairy.data;

import java.util.List;

/**
 * @author Olga Maciaszek-Sharma
 * @since 23.04.15
 */
public interface DataMaster {

	String getString(String key);

	List<String> getStringList(String key);

	<T> T getValuesOfType(String dataKey, final String type, final Class<T> resultClass);

	String getRandomValue(String key);


}
