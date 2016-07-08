package io.codearte.jfairy.producer.payment

import io.codearte.jfairy.data.DataMaster
import io.codearte.jfairy.data.MapBasedDataMaster
import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.DateProducer
import org.joda.time.DateTime
import spock.lang.Specification

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-17
 */
class CreditCardProviderSpec extends Specification {

	public static final DateTime EXPIRY_DATE = new DateTime("2009-02-11T23:59:59.999")

	private DataMaster dataMaster
	private DateProducer dateProducer
	private CreditCardProvider creditCardProvider;

	def setup() {
		dataMaster = new MapBasedDataMaster(new BaseProducer(new Random()))
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
