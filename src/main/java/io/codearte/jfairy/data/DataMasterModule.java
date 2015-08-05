package io.codearte.jfairy.data;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import java.util.Random;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2015-05-20
 */
public class DataMasterModule extends AbstractModule {

	private Random random;

	@Inject
	public DataMasterModule(Random random) {
		this.random = random;
	}

	@Override
	protected void configure() {
		bind(Random.class).toInstance(random);
	}
}
