package eu.codearte.jfairy.producer.net

import org.apache.commons.validator.routines.InetAddressValidator
import eu.codearte.jfairy.producer.BaseProducer
import spock.lang.Specification

class NetworkSpec extends Specification {

	def ipValidator = InetAddressValidator.getInstance();

	def ipNumber = new IPNumberProducer(new BaseProducer(new Random()));
	def network = new NetworkProducer(ipNumber)

	def "Should generate proper ip number"() {
		def ip = network.ipAddress()

		expect:
			ip
			ipValidator.isValid(ip)

	}
}
