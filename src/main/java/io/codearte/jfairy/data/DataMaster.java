package io.codearte.jfairy.data;

import java.util.List;

/**
 * @author omaciaszek
 * @since 23.04.15
 */
public interface DataMaster {

	String getString(String key);

	List<String> getStringList(String key);

	String getValuesOfType(String dataKey, final String type);

	String getRandomValue(String key);


}
