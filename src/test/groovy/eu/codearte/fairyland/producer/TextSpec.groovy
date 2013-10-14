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
        text.loremIpsum()
        text.words()
        text.words(100)
        text.sentence()
        text.sentence(100)
        text.paragraph()
        text.paragraph(100)
    }

    def "should limit generated text"() {
        when:
        def text = Hook.create().text().limit(10)
        then:
        text.loremIpsum().length() == 10
        text.words(100).length() == 10
        text.sentence(100).length() == 10
        text.paragraph(100).length() == 10
    }

}
