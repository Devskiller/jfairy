package io.codearte.jfairy.producer.net

import io.codearte.jfairy.producer.BaseProducer
import org.apache.commons.validator.routines.InetAddressValidator
import spock.lang.Specification

class NetworkSpec extends Specification {

	private InetAddressValidator ipValidator = InetAddressValidator.getInstance();

	private IPNumberProducer ipNumber = new IPNumberProducer(new BaseProducer(new Random()));
	private NetworkProducer network = new NetworkProducer(ipNumber)

	def "Should generate proper ip number"() {
		String ip = network.ipAddress()

		expect:
			ip
			ipValidator.isValid(ip)

	}

	def "Should generate proper url"() {
		String url = network.url(true)

		expect:
			url.startsWith("https://") && url.endsWith(".com")

	}
}
