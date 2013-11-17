/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.DataMaster
import eu.codearte.fairyland.producer.person.Person
import spock.lang.Specification

class RandomDataGeneratorSpec extends Specification {

	def baseProducer = Spy(BaseProducer);
	def data = Spy(DataMaster, constructorArgs: [baseProducer])

	def setup() {
		baseProducer.randomBetween() >> 0
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
