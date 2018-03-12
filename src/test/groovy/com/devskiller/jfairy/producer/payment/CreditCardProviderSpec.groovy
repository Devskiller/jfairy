package com.devskiller.jfairy.producer.payment

import java.time.LocalDateTime

import spock.lang.Specification

import com.devskiller.jfairy.data.DataMaster
import com.devskiller.jfairy.data.MapBasedDataMaster
import com.devskiller.jfairy.producer.BaseProducer
import com.devskiller.jfairy.producer.DateProducer
import com.devskiller.jfairy.producer.RandomGenerator

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-17
 */
class CreditCardProviderSpec extends Specification {

	public static final LocalDateTime EXPIRY_DATE = LocalDateTime.parse("2009-02-11T23:59:59.999")

	private DataMaster dataMaster
	private DateProducer dateProducer
	private CreditCardProvider creditCardProvider;

	def setup() {
		dataMaster = new MapBasedDataMaster(new BaseProducer(new RandomGenerator()))
		dateProducer = Mock(DateProducer)
		dataMaster.readResources("jfairy.yml")
		creditCardProvider = new CreditCardProvider(dataMaster, dateProducer)
	}

	def "should return credit card provider"() {
		when:
			CreditCard creditCard = creditCardProvider.get()
		then:
			creditCard.vendor
	}

	def "should return card expiry date"() {
		given:
			dateProducer.randomDateBetweenNowAndFuturePeriod(_) >> EXPIRY_DATE
		when:
			CreditCard creditCard = creditCardProvider.get()
		then:
			creditCard.expiryDate == EXPIRY_DATE
	}

	def "should return card expiry date string"() {
		given:
			dateProducer.randomDateBetweenNowAndFuturePeriod(_) >> EXPIRY_DATE
		when:
			CreditCard creditCard = creditCardProvider.get()
		then:
			creditCard.expiryDateAsString == "02/09"
	}


}
