/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.DataMaster
import eu.codearte.fairyland.producer.util.CalendarGenerator
import eu.codearte.fairyland.producer.util.DateProvider
import spock.lang.Specification

class RandomDataGeneratorSpec extends Specification {

    def data = Mock(DataMaster)
    def randomGenerator = new RandomGenerator(1001L);
    def randomCalendar = new CalendarGenerator(randomGenerator, new DateProvider())

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
