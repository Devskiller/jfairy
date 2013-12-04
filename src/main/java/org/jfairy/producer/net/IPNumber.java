package org.jfairy.producer.net;

import com.google.inject.Inject;
import org.jfairy.producer.BaseProducer;

class IPNumber {

	private static final String IP_FORMAT = "%s.%s.%s.%s";
	private static final int MAX = 0xFF;

	private final BaseProducer baseProducer;

	@Inject
	public IPNumber(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	public String generate() {
		return String.format(IP_FORMAT, ipNumberPart(), ipNumberPart(), ipNumberPart(), ipNumberPart());
	}

	private int ipNumberPart() {
		return baseProducer.randomInt(MAX);
	}

}
