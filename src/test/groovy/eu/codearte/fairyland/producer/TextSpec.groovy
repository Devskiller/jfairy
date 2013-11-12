package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Fairy
import eu.codearte.fairyland.producer.text.Text
import spock.lang.Specification

class TextSpec extends Specification {

   private Text text;

   def setup() {
      text = Fairy.create().text()
   }

   def "should instantiate Text producer with Text object"() {
      expect:
      text instanceof Text
   }

   def "should be sure that data exists"() {
      expect:
      text.loremIpsum()
      text.word()
      text.word(100)
      text.sentence()
      text.sentence(100)
      text.paragraph()
      text.paragraph(100)
   }

   def "should limit generated text"() {
      when:
      text.limitedTo(10)
      then:
      text.loremIpsum().length() == 10
      text.word(100).length() == 10
      text.sentence(100).length() == 10
      text.paragraph(100).length() == 10
   }

   def "should generate word"() {
      expect:
      text.word()
   }

   def "should generate sentence"() {
      expect:
      text.sentence()
   }

   def "should generate paragraph"() {
      expect:
      text.paragraph()
   }

}
