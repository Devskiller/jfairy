package io.codearte.jfairy.producer.net

import io.codearte.jfairy.producer.BaseProducer
import org.apache.commons.validator.routines.InetAddressValidator
import spock.lang.Specification

class NetworkSpec extends Specification {

	InetAddressValidator ipValidator = InetAddressValidator.getInstance();

	IPNumberProducer ipNumber = new IPNumberProducer(new BaseProducer(new Random()));
	NetworkProducer network = new NetworkProducer(ipNumber)

	def "Should generate proper ip number"() {
		String ip = network.ipAddress()

		expect:
			ip
			ipValidator.isValid(ip)

	}
}
