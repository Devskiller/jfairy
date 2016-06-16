package io.codearte.jfairy.producer.person.locale.pl;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberFactory;
import io.codearte.jfairy.producer.person.NationalIdentificationNumberProperties;

import javax.inject.Inject;

public class PeselFactory implements NationalIdentificationNumberFactory {

	private final BaseProducer baseProducer;
	private final DateProducer dateProducer;

	@Inject
	public PeselFactory(BaseProducer baseProducer, DateProducer dateProducer) {
		this.baseProducer = baseProducer;
		this.dateProducer = dateProducer;
	}

	@Override
	public PeselProvider produceNationalIdentificationNumberProvider(NationalIdentificationNumberProperties.Property... properties) {
		return new PeselProvider(dateProducer, baseProducer, properties);
	}

}
