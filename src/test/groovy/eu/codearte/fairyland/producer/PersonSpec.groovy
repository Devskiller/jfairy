package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Fairy
import eu.codearte.fairyland.producer.person.Person
import spock.lang.Ignore
import spock.lang.Specification

class PersonSpec extends Specification {
    def "should instantiate Person producer"() {
        when:
        def person = Fairy.create().produce(PersonProducer.class)
        then:
        person instanceof PersonProducer
    }

    def "should instantiate Person producer with person"() {
        when:
        def person = Fairy.create().person()
        then:
        person instanceof Person
    }

    def "should be sure that fullName is proper"() {
        when:
        def person = Fairy.create().person()
        then:
        person.email()
        "${person.firstName()} ${person.lastName()}" == person.fullName()
    }

    @Ignore
    def "second generated name should be different"() {
        setup:
        def fairy = Fairy.create()
        expect:
        fairy.person().firstName() != fairy.person().firstName()
    }

    def "should be sure that data exists"() {
        when:
        def person = Fairy.create().person()
        then:
        person.firstName()
        person.lastName()
        person.fullName()
        person.email()
        person.sex()
        person.isMale() || person.isFemale()
    }

}
