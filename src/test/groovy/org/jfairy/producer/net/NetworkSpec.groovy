package org.jfairy.producer.net

import org.jfairy.producer.BaseProducer
import org.apache.commons.validator.routines.InetAddressValidator
import spock.lang.Specification

class NetworkSpec extends Specification {

	def ipValidator = InetAddressValidator.getInstance();

	def ipNumber = new IPNumber(new BaseProducer(new Random()));
	def network = new Network(ipNumber)

	def "Should generate proper ip number"() {
		def ip = network.ipAddress()

		expect:
		ip
		ipValidator.isValid(ip)

	}
}
