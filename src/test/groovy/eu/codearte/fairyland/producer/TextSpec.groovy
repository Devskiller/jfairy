/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Fairy
import eu.codearte.fairyland.producer.text.Text
import spock.lang.Specification

class TextSpec extends Specification {

    private Text text;

    def setup() {
        text = Fairy.create().text();
    }

    def "should generate word"() {
        expect:
        text.word() == "leo tempus ligula"
    }
}
