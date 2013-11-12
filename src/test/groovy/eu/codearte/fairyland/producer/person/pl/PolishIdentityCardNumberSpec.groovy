/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person.pl

import eu.codearte.fairyland.producer.util.RandomGenerator
import org.joda.time.DateTime
import spock.lang.Specification

import static eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber.BEGIN
import static eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber.PREFIXES_BY_YEAR

/**
 * @author mariuszs
 * @since 30.10.13.
 */
class PolishIdentityCardNumberSpec extends Specification {

   def randomGenerator = Mock(RandomGenerator)

   /**
    * http://en.wikipedia.org/wiki/Polish_identity_card
    */
   void "should generate proper id number"() {
      def max = (2013 - BEGIN) * PREFIXES_BY_YEAR
      setup:
      randomGenerator.randomBetween(max, max + PREFIXES_BY_YEAR) >> 26 // ABA
      randomGenerator.randomBetween(0, 99999) >> 0
      when:
      PolishIdentityCardNumber generator = new PolishIdentityCardNumber(randomGenerator)
      def id = generator.generate(DateTime.now())
      then:
      id == "ABA300000"
      generator.isValid(id)
   }
}
