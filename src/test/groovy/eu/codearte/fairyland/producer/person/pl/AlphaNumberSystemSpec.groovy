/*
 * Copyright (c) 2013 Codearte and authors
 */

package eu.codearte.fairyland.producer.person.pl

import spock.lang.Specification
import spock.lang.Unroll

import static eu.codearte.fairyland.producer.person.pl.AlphaNumberSystem.convertToString

/**
 * @author mariuszs
 * @since 01.11.13.
 */
class AlphaNumberSystemSpec extends Specification {

   @Unroll
   void "Number #x should be converted to #y"() {
      expect:
      convertToString(x, 26) == y;
      where:
      x  | y
      0  | "A"
      1  | "B"
      2  | "C"
      25 | "Z"
      26 | "BA"

   }
}
