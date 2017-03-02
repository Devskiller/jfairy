package io.codearte.jfairy.producer.net;

import javax.inject.Inject;

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

	/**
	 * Add a simple url generator
	 * @return
	 */
	public String url() {
		String h = ipAddress().replaceAll("\\.", "");
		char[] chars = h.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			chars[i] = (char)(c + 97);
		}
		return "http://" + String.valueOf(chars) + ".com";
	}
}
