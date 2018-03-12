package com.devskiller.jfairy

import spock.lang.Specification

import com.devskiller.jfairy.data.MapBasedDataMaster
import com.devskiller.jfairy.producer.RandomGenerator

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
