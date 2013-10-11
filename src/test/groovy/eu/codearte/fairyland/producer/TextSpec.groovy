package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Hook
import spock.lang.Specification

class TextSpec extends Specification {
    def "should instantiate Text producer"() {
        when:
        def text = Hook.create().produce(Text.class)
        then:
        text instanceof Text
    }

    def "should instantiate Text producer with person"() {
        when:
        def text = Hook.create().text()
        then:
        text instanceof Text
    }

    def "should be sure that data exists"() {
        when:
        def text = Hook.create().text()
        then:
        text.loremIpsum
        text.loremIpsum(10).length() == 10
        text.word()
        text.words(3)
        text.sentence(3)
    }

}
