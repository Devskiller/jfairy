package eu.codearte.fairyland.producer

import org.apache.commons.validator.routines.InetAddressValidator
import spock.lang.Specification

class IPNumberSpec extends Specification {

	InetAddressValidator ipValidator = InetAddressValidator.getInstance();

	def "Should generate proper ip number"() {
		IPNumber ipNumber = new IPNumber(new BaseProducer());
		def ip = ipNumber.generate()

		expect:
		ip
		ipValidator.isValid(ip)

	}
}
