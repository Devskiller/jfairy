/*
 * Copyright (c) 2013. Codearte
 */

package org.jfairy.data

import org.jfairy.producer.BaseProducer
import org.jfairy.producer.person.Person
import spock.lang.Specification

class DataMasterSpec extends Specification {

	def baseProducer = Spy(BaseProducer, constructorArgs: [new Random()]);
	def data = Spy(DataMaster, constructorArgs: [baseProducer])

	def setup() {
		baseProducer.randomBetween() >> 0
	}

	def "should read first names"() {
		when:
		DataMaster dataMaster = new DataMaster();
		dataMaster.readResources("fairyland_en.yml")

		def firstNames = dataMaster.getStringMap(Person.FIRST_NAME)
		then:
		firstNames.size() > 0
		firstNames.keySet().size() > 0
	}

	def "should return men"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		def male = data.getValuesOfType(Person.FIRST_NAME, "male");

		then:
		male == "Mark"
	}

	def "should return one of women"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		def female = data.getValuesOfType(Person.FIRST_NAME, "female");

		then:
		female == "Ana" || "Ivon"
	}

}
