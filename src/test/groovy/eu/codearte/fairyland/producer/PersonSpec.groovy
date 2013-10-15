package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Fairy
import spock.lang.Specification

/**
 * @author Codearte
 * @since 2013-10-07
 */
class PersonSpec extends Specification {
  def "should instantiate Person producer"() {
    when:
    def person = Fairy.create().produce(Person.class)
    then:
    person instanceof Person
  }

  def "should instantiate Person producer with person"() {
    when:
    def person = Fairy.create().person()
    then:
    person instanceof Person
  }

  def "should be sure that data exists"() {
    when:
    def person = Fairy.create().person()
    then:
    person.firstName()
    person.lastName()
    person.fullName()
    person.email()
  }

    def "should be sure that fullName is proper"() {
        when:
        def person = Fairy.create().person()
        then:
        person.email()
        "${person.firstName()} ${person.lastName()}" == person.fullName()
    }
}
