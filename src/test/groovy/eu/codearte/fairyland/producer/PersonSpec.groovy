package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Hook
import eu.codearte.fairyland.Person
import spock.lang.Specification

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-07
 */
class PersonSpec extends Specification {
  def "should instantiate Person producer"() {
    when:
    def person = Hook.director().produce(Person)
    then:
    person instanceof Person
  }

  def "should instantiate Person producer with person()"() {
    when:
    def person = Hook.director().person()
    then:
    person instanceof Person
  }
}
