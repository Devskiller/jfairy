package io.codearte.jfairy.dataProvider.net;

import io.codearte.jfairy.dataProvider.BaseProducer;

import javax.inject.Inject;

class IPNumberProducer {

	private static final String IP_FORMAT = "%s.%s.%s.%s";
	private static final int MAX = 0xFF;

	private final BaseProducer baseProducer;

	@Inject
	public IPNumberProducer(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	public String generate() {
		return String.format(IP_FORMAT, ipNumberPart(), ipNumberPart(), ipNumberPart(), ipNumberPart());
	}

	private int ipNumberPart() {
		return baseProducer.randomInt(MAX);
	}

}
