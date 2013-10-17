/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.DataMaster
import spock.lang.Specification

class RandomDataGeneratorSpec extends Specification {

    def data = Mock(DataMaster)
    def randomGenerator = new RandomGenerator(1001L);

    def "should return men"() {
        setup:
        data.getStringMap(DataMaster.FIRST_NAME) >> [Ana: 'female', Mark: 'male', Ivon: 'female']

        when:
        RandomDataGenerator generator = new RandomDataGenerator(data, randomGenerator);
        def male = generator.getValuesOfType(DataMaster.FIRST_NAME, "male");

        then:
        male == "Mark"
    }

    def "should return one of women"() {
        setup:
        data.getStringMap(DataMaster.FIRST_NAME) >> [Ana: 'female', Mark: 'male', Ivon: 'female']

        when:
        RandomDataGenerator generator = new RandomDataGenerator(data, randomGenerator);
        def female = generator.getValuesOfType(DataMaster.FIRST_NAME, "female");

        then:
        female == "Ana" || "Ivon"
    }

    def "should return sex of Ana"() {
        setup:
        data.getStringMap(DataMaster.FIRST_NAME) >> [Ana: 'female']

        when:
        RandomDataGenerator generator = new RandomDataGenerator(data, randomGenerator);
        def sex = generator.getTypeOfValue(DataMaster.FIRST_NAME, "Ana");

        then:
        sex == "female"
    }

    def "should return sex of Mark"() {
        setup:
        data.getStringMap(DataMaster.FIRST_NAME) >> [Mark: 'male']

        when:
        RandomDataGenerator generator = new RandomDataGenerator(data, randomGenerator);
        def sex = generator.getTypeOfValue(DataMaster.FIRST_NAME, "Mark");

        then:
        sex == "male"
    }
}
