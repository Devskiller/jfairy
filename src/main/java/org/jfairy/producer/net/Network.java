package org.jfairy.producer.net;

import com.google.inject.Inject;

/**
 *
 * TODO: Add emails
 *
 */
public class Network {

	private final IPNumber ipNumber;

	@Inject
	public Network(IPNumber ipNumber) {
		this.ipNumber = ipNumber;
	}

	public String ipAddress() {
		return ipNumber.generate();
	}

}
