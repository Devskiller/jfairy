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
	 * Example: networkProducer.url(baseProducer.trueOrFalse())
	 *
	 * @return
	 */
	public String url(boolean isHttps) {
		String mergedIP = ipAddress().replaceAll("\\.", "");
		char[] domainChars = mergedIP.toCharArray();
		for (int i = 0; i < domainChars.length; i++) {
			char c = domainChars[i];
			domainChars[i] = (char) (c + 97);
		}

		String domain = String.valueOf(domainChars);
		if (isHttps)
			return "https://" + domain + ".com";
		else
			return "http://" + domain + ".com";
	}
}
