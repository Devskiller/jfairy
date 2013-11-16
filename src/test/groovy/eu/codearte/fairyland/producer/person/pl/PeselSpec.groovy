package eu.codearte.fairyland.producer.person.pl

import eu.codearte.fairyland.producer.BaseProducer
import eu.codearte.fairyland.producer.person.Person
import eu.codearte.fairyland.producer.person.locale.pl.Pesel
import eu.codearte.fairyland.producer.util.DateGenerator
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.Unroll

import static eu.codearte.fairyland.producer.person.locale.pl.Pesel.isValid

class PeselSpec extends Specification {

	def randomGenerator = Mock(BaseProducer);
	def dateGenerator = Mock(DateGenerator);
	def Pesel generator = new Pesel(dateGenerator, randomGenerator);

	@Unroll def "should validate #pesel as #valid"() {

		isValid(pesel) == valid

		where:
		pesel         | valid
		"44051401359" | true
		"44051401358" | false
		"44051401458" | true
		"44052401458" | false

	}

	def "should generate good pesel"() {
		when:
		def pesel = generator.generate(DateTime.now(), Person.Sex.MALE);
		then:
		pesel
		isValid(pesel)
	}

}
