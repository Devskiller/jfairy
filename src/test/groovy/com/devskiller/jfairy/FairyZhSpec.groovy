package com.devskiller.jfairy

import spock.lang.Specification

import com.devskiller.jfairy.producer.company.Company
import com.devskiller.jfairy.producer.person.Person

/**
 *
 * com.devskiller.jfairy.FairyZhSpec
 * @author lhfcws
 * @since 2017/3/2
 */
class FairyZhSpec extends Specification {
	private final int SEED = 0
	private Fairy fairy = Fairy.builder().withRandomSeed(SEED).withLocale(Locale.CHINA).build()

	/********************
	 * Person
	 */
	def "Should create Chinese name"() {
		when:
		Person person = fairy.person()
		then:
		person.fullName == '正宇 郑'
	}

	def "Should create Chinese id card"() {
		when:
		Person person = fairy.person()
		then:
		person.nationalIdentityCardNumber.length() == 18
	}

	def "Should create empty since Chinese id number is the only citizen id in China"() {
		when:
		Person person = fairy.person()
		then:
		person.nationalIdentificationNumber.length() == 0
	}

	def "Should create Chinese address"() {
		when:
		Person person = fairy.person()
		then:
		person.address.addressLine1 == '南京市外环西路85号'
	}

	def "Should create Chinese city"() {
		when:
		Person person = fairy.person()
		then:
		person.address.city == '南京'
	}

	/********************
	 * Company
	 */
	def "Should create Chinese company name"() {
		when:
		Company company = fairy.company()
		then:
		company.name == "数说故事 金融投资有限公司"
	}

	def "Should create Chinese company url"() {
		when:
		Company company = fairy.company()
		then:
		company.url == "http://www.65708BF465.net"
	}

	def "Should create Chinese company vat"() {
		when:
		Company company = fairy.company()
		then:
		company.vatIdentificationNumber.length() == 15
	}
}
