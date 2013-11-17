/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland

import eu.codearte.fairyland.data.DataMaster
import eu.codearte.fairyland.producer.person.Person
import spock.lang.Specification

class DataMasterSpec extends Specification {

	def "should read first names"() {
		when:
		DataMaster dataMaster = new DataMaster();
		dataMaster.readResources("fairyland_en.yml")

		def firstNames = dataMaster.getStringMap(Person.FIRST_NAME)
		then:
		firstNames.size() > 0
		firstNames.keySet().size() > 0
	}
}
