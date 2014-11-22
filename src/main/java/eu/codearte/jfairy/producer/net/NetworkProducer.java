package eu.codearte.jfairy.producer.net;

import com.google.inject.Inject;

/**
 * TODO: Add emails
 */
public class NetworkProducer {

	private final IPNumberProducer ipNumberProducer;

	@Inject
	public NetworkProducer(IPNumberProducer ipNumberProducer) {
		this.ipNumberProducer = ipNumberProducer;
	}

	public String ipAddress() {
		return ipNumberProducer.generate();
	}

}
