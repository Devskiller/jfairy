/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.producer.util.DataMaster
import eu.codearte.fairyland.producer.util.DateGenerator
import eu.codearte.fairyland.producer.util.RandomDataGenerator
import eu.codearte.fairyland.producer.util.RandomGenerator
import eu.codearte.fairyland.producer.util.TimeProvider
import spock.lang.Specification

class RandomDataGeneratorSpec extends Specification {

	def data = Mock(DataMaster)
	def randomGenerator = new RandomGenerator(1001L);
	def randomCalendar = new DateGenerator(randomGenerator, new TimeProvider())

	def "should return men"() {
		setup:
		data.getStringMap(DataMaster.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		RandomDataGenerator generator = new RandomDataGenerator(data, randomGenerator, randomCalendar);
		def male = generator.getValuesOfType(DataMaster.FIRST_NAME, "male");

		then:
		male == "Mark"
	}

	def "should return one of women"() {
		setup:
		data.getStringMap(DataMaster.FIRST_NAME) >> [female: ['Ana', 'Ivon'], male: ['Mark']]

		when:
		RandomDataGenerator generator = new RandomDataGenerator(data, randomGenerator, randomCalendar);
		def female = generator.getValuesOfType(DataMaster.FIRST_NAME, "female");

		then:
		female == "Ana" || "Ivon"
	}

}
