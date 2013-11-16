/*
 * Copyright (c) 2013. Codearte
 */

package eu.codearte.fairyland

import eu.codearte.fairyland.producer.util.DataMaster
import spock.lang.Specification

class DataMasterSpec extends Specification {

	def "should read first names"() {
		when:
		DataMaster dataMaster = new DataMaster();
		dataMaster.readResources("fairyland_en.yml")

		def firstNames = dataMaster.getStringMap(DataMaster.FIRST_NAME)
		then:
		firstNames.size() > 0
		firstNames.keySet().size() > 0
	}
}
