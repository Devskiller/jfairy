package io.codearte.jfairy.dataProvider.payment

import io.codearte.jfairy.data.DataMaster
import io.codearte.jfairy.data.MapBasedDataMaster
import io.codearte.jfairy.dataProvider.BaseProducer
import io.codearte.jfairy.dataProvider.DateProducer
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
		dataMaster = new MapBasedDataMaster(new BaseProducer(new Random()))
		dateProducer = Mock(DateProducer)
		dataMaster.readResources("jfairy.yml")
	}

	def "should return credit card provider"() {
		when:
			CreditCard cardProducer = new CreditCard(dataMaster, dateProducer)
		then:
			cardProducer.vendor()
	}

	def "should return card expiry date"() {
		given:
			dateProducer.randomDateBetweenNowAndFuturePeriod(_) >> EXPIRY_DATE
		when:
			CreditCard cardProducer = new CreditCard(dataMaster, dateProducer)
		then:
			cardProducer.expiryDate() == EXPIRY_DATE
	}

	def "should return card expiry date string"() {
		given:
			dateProducer.randomDateBetweenNowAndFuturePeriod(_) >> EXPIRY_DATE
		when:
			CreditCard cardProducer = new CreditCard(dataMaster, dateProducer)
		then:
			cardProducer.expiryDateAsString() == "02/09"
	}


}
