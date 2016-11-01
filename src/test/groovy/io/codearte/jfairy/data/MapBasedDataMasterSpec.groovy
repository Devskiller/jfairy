/*
 * Copyright (c) 2013. Codearte
 */

package io.codearte.jfairy.data

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.person.PersonProvider
import spock.lang.Specification

class MapBasedDataMasterSpec extends Specification {

	private BaseProducer baseProducer = Spy(BaseProducer, constructorArgs: [new Random()]);
	private MapBasedDataMaster data = Spy(MapBasedDataMaster, constructorArgs: [baseProducer])

	def setup() {
		baseProducer.randomBetween() >> 0
	}

	def "should read first names"() {
		when:
			DataMaster dataMaster = new MapBasedDataMaster();
			dataMaster.readResources("jfairy_en.yml")

			Map<String, List<String>> firstNames = dataMaster.getStringMap(PersonProvider.FIRST_NAME)
		then:
			firstNames.size() > 0
			firstNames.keySet().size() > 0
	}

	def "should return men"() {
		setup:
			data.getStringMap(PersonProvider.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
			String male = data.getValuesOfType(PersonProvider.FIRST_NAME, "male");

		then:
			male == "Mark"
	}

	def "should return one of women"() {
		setup:
			data.getStringMap(PersonProvider.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
			String female = data.getValuesOfType(PersonProvider.FIRST_NAME, "female");

		then:
			female == "Ana" || "Ivon"
	}

}
