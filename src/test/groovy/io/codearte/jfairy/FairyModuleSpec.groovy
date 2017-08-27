package io.codearte.jfairy

import io.codearte.jfairy.data.MapBasedDataMaster
import io.codearte.jfairy.producer.RandomGenerator
import spock.lang.Specification

/**
 * @author Olga Maciaszek-Sharma
 @since 15.03.15
 */
class FairyModuleSpec extends Specification {

	private MapBasedDataMaster mapBasedDataMaster = Stub(MapBasedDataMaster)

	def "should generate appropriate FairyModule for locale"() {
		when:
			FairyModule fairyModule = Bootstrap.getFairyModuleForLocale(mapBasedDataMaster, Locale.forLanguageTag(locale), new RandomGenerator())

		then:
			fairyModule.getClass() == module

		where:
			locale | module
			"en"   | EnFairyModule.class
			"pl"   | PlFairyModule.class
			"es"   | EsFairyModule.class
	}

	def "should generate appropriate FairyModule when no locale passed"() {
		when:
			FairyModule fairyModule = Bootstrap.getFairyModuleForLocale(mapBasedDataMaster, Locale.forLanguageTag(locale), new RandomGenerator())

		then:
			fairyModule.getClass() == module

		where:
			locale | module
			"en"   | EnFairyModule.class
			"pl"   | PlFairyModule.class
			"es"   | EsFairyModule.class
	}

}
