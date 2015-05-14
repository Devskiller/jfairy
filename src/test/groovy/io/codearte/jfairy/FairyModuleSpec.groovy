package io.codearte.jfairy

import spock.lang.Specification

/**
 * @author omaciaszek
 * @since 15.03.15
 */
class FairyModuleSpec extends Specification {

	def "should generate appropriate FairyModule for locale"() {
		when:
			FairyModule fairyModule = Bootstrap.getFairyModuleForLocale(Locale.forLanguageTag(locale), new Random())

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
			FairyModule fairyModule = Bootstrap.getFairyModuleForLocale(Locale.forLanguageTag(locale), new Random())

		then:
			fairyModule.getClass() == module

		where:
			locale | module
			"en"   | EnFairyModule.class
			"pl"   | PlFairyModule.class
			"es"   | EsFairyModule.class
	}

}
