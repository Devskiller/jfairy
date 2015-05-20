package io.codearte.jfairy.data;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author Olga Maciaszek-Sharma
 * @since 2015-05-20
 */
public class DataMasterModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().build(MapBasedDataMasterFactory.class));
	}
}
