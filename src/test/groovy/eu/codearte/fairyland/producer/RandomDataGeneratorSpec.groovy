/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.producer.person.Person
import eu.codearte.fairyland.producer.util.DataMaster
import eu.codearte.fairyland.producer.util.DateGenerator
import eu.codearte.fairyland.producer.util.RandomDataGenerator
import eu.codearte.fairyland.producer.util.TimeProvider
import spock.lang.Specification

class RandomDataGeneratorSpec extends Specification {

	def data = Mock(DataMaster)
	def baseProducer = Spy(BaseProducer);
	def randomCalendar = new DateGenerator(baseProducer, new TimeProvider())

	def setup() {
		baseProducer.randomBetween() >> 0
	}

	def "should return men"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		RandomDataGenerator generator = new RandomDataGenerator(data, baseProducer, randomCalendar);
		def male = generator.getValuesOfType(Person.FIRST_NAME, "male");

		then:
		male == "Mark"
	}

	def "should return one of women"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		RandomDataGenerator generator = new RandomDataGenerator(data, baseProducer, randomCalendar);
		def female = generator.getValuesOfType(Person.FIRST_NAME, "female");

		then:
		female == "Ana" || "Ivon"
	}

}
