package eu.codearte.fairyland.producer

import eu.codearte.fairyland.Fairy
import spock.lang.Specification

/**
 * @author Codearte
 * @since 2013-10-07
 */
class CompanySpec extends Specification {
   def "should instantiate Company producer"() {
      when:
      def company = Fairy.create().company()
      then:
      company instanceof Company
   }

   def "should be sure that data exists"() {
      when:
      def company = Fairy.create().company()
      then:
      company.name()
      company.email()
      company.url()
      company.vatIdentificationNumber()
   }
}
