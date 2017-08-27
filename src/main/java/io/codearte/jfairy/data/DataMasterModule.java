package io.codearte.jfairy.data;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import io.codearte.jfairy.producer.RandomGenerator;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2015-05-20
 */
public class DataMasterModule extends AbstractModule {

	private RandomGenerator randomGenerator;

	@Inject
	public DataMasterModule(RandomGenerator randomGenerator) {
		this.randomGenerator = randomGenerator;
	}

	@Override
	protected void configure() {
		bind(RandomGenerator.class).toInstance(randomGenerator);
	}
}
