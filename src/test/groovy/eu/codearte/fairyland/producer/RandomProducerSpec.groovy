/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.RandomDataGenerator
import eu.codearte.fairyland.producer.person.Person
import eu.codearte.fairyland.DataMaster
import spock.lang.Specification

class RandomProducerSpec extends Specification {

	def data = Mock(DataMaster)
	def randomGenerator = Mock(RandomGenerator);
	def dateGenerator = new DateGenerator(new TimeProvider(), randomGenerator)
	def randomProducer = new RandomProducer(randomGenerator, dateGenerator, new RandomDataGenerator(data), Mock(FakerProducer));

	def setup() {
		randomGenerator.randomBetween() >> 0
	}
	                //FIXME MOve to RandomProducerSpec
	def "should return men"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		def male = randomProducer.getValuesOfType(Person.FIRST_NAME, "male");

		then:
		male == "Mark"
	}

	def "should return one of women"() {
		setup:
		data.getStringMap(Person.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		def female = randomProducer.getValuesOfType(Person.FIRST_NAME, "female");

		then:
		female == "Ana" || "Ivon"
	}

}
