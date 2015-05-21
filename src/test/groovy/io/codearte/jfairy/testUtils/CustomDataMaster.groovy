package io.codearte.jfairy.testUtils

import io.codearte.jfairy.data.DataMaster

/**
 * @author Olga Maciaszek-Sharma
 @since 13.05.15
 */
class CustomDataMaster implements DataMaster {

	@Override
	String getString(String key) {
		return TestFixture.CUSTOM_STRING
	}

	@Override
	List<String> getStringList(String key) {
		return Arrays.asList(TestFixture.CUSTOM_STRING)
	}

	@Override
	String getValuesOfType(String dataKey, String type) {
		return TestFixture.CUSTOM_STRING
	}

	@Override
	String getRandomValue(String key) {
		return TestFixture.CUSTOM_STRING
	}
}
