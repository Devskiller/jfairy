package eu.codearte.fairyland.producer;

import com.google.inject.Inject;

public class IPNumber {

	private static final String IP_FORMAT = "%s.%s.%s.%s";

	private final BaseProducer baseProducer;

	@Inject
	public IPNumber(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	public String generate() {
		return String.format(IP_FORMAT, ipNumberPart(), ipNumberPart(), ipNumberPart(), ipNumberPart());
	}

	private int ipNumberPart() {
		return baseProducer.randomInt(255);
	}

}
