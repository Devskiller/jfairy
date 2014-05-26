package org.jfairy.producer.payment

import org.jfairy.data.DataMaster
import org.jfairy.producer.BaseProducer
import org.jfairy.producer.DateProducer
import org.joda.time.DateTime
import spock.lang.Specification

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-17
 */
class CreditCardProducerSpec extends Specification {

	public static final DateTime EXPIRY_DATE = new DateTime("2009-02-11T23:59:59.999")

	private DataMaster dataMaster
	private DateProducer dateProducer

	def setup() {
		dataMaster = new DataMaster(new BaseProducer(new Random()))
		dateProducer = Mock(DateProducer)
		dataMaster.readResources("fairyland.yml")
	}

	def "should return credit card provider"() {
		when:
			CreditCardProducer cardProducer = new CreditCardProducer(dataMaster, dateProducer)
		then:
			cardProducer.vendor()
	}

	def "should return card expiry date"() {
		given:
			dateProducer.randomDateBetweenNowAndFuturePeriod(_) >> EXPIRY_DATE
		when:
			CreditCardProducer cardProducer = new CreditCardProducer(dataMaster, dateProducer)
		then:
			cardProducer.expiryDate() == EXPIRY_DATE
	}

	def "should return card expiry date string"() {
		given:
			dateProducer.randomDateBetweenNowAndFuturePeriod(_) >> EXPIRY_DATE
		when:
			CreditCardProducer cardProducer = new CreditCardProducer(dataMaster, dateProducer)
		then:
			cardProducer.expiryDateAsString() == "02/09"
	}


}
