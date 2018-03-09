package io.codearte.jfairy.producer.payment

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.data.DataMaster
import io.codearte.jfairy.data.MapBasedDataMaster
import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.RandomGenerator
import org.iban4j.IbanUtil
import spock.lang.Specification

class IBANSpec extends Specification {

	private DataMaster dataMaster

	def setup() {
		BaseProducer baseProducer = new BaseProducer(new RandomGenerator())
		dataMaster = new MapBasedDataMaster(baseProducer);
		dataMaster.readResources("jfairy_pl.yml")
	}

	/**
	 * Austria	20	16n
	 * ATkk bbbb bccc cccc cccc
	 *
	 * b = National bank code
	 c = Account number
	 */
	def "should return valid iban"() {
		when:
			IBANProvider iban = new DefaultIBANProvider(dataMaster,
				IBANProperties.accountNumber("00234573201"),
				IBANProperties.country("AT")
			);

		then:
			IbanUtil.validate(iban.get().ibanNumber);
	}

	/**
	 * Poland	28	24n	PLkk bbbs sssx cccc cccc cccc cccc	b = National bank code
	 s = Branch code
	 x = National check digit
	 c = Account number,

	 PLkk bbbssssx cccccccccccccccc
	 PL60 11401010 1111000234573201
	 */

	def "should return valid polish iban"() {
		when:
			IBANProvider iban = new DefaultIBANProvider(dataMaster)
		then:
			IbanUtil.validate(iban.get().ibanNumber);
	}

	def "should be usable directly from Fairy"() {
		when:
			String number = Fairy.create().iban(IBANProperties.country("PL")).ibanNumber
		then:
			number.startsWith('PL')
	}

	def "should ignore countries not supporting iban"() {
		when:
			def iban = Fairy.create().iban(IBANProperties.country("US"))
		then:
			iban == null
	}

	def "should set proper country for specified language"() {
		when:
			String number = Fairy.create().iban(IBANProperties.language("DE")).ibanNumber
		then:
			number.startsWith('DE')
	}

	def "should set proper country for according to selected language"() {
		when:
			String number = Fairy.create(Locale.ENGLISH).iban().ibanNumber
		then:
			number.startsWith('GB')
	}
}
