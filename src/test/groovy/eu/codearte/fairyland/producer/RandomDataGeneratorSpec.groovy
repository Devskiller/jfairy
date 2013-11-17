/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.RandomDataGenerator
import eu.codearte.fairyland.producer.person.Person
import eu.codearte.fairyland.DataMaster
import spock.lang.Specification

class RandomDataGeneratorSpec extends Specification {

	def data = Mock(DataMaster)
	def randomGenerator = Mock(RandomGenerator);
	def baseProducer = new RandomProducer(randomGenerator, dateGenerator, Mock(FakerProducer));
	def dateGenerator = new DateGenerator(new TimeProvider(), randomGenerator)

	def setup() {
		randomGenerator.randomBetween() >> 0
	}

	def "should return men"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		RandomDataGenerator generator = new RandomDataGenerator(data, baseProducer);
		def male = generator.getValuesOfType(Person.FIRST_NAME, "male");

		then:
		male == "Mark"
	}

	def "should return one of women"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		RandomDataGenerator generator = new RandomDataGenerator(data, baseProducer);
		def female = generator.getValuesOfType(Person.FIRST_NAME, "female");

		then:
		female == "Ana" || "Ivon"
	}

}
