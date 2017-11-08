package io.codearte.jfairy.producer.company.locale.ge;

import com.google.inject.Inject;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.VATIdentificationNumberProvider;

public class GeVATIdentificationNumberProvider implements VATIdentificationNumberProvider {

	private final BaseProducer baseProducer;

	@Inject
	public GeVATIdentificationNumberProvider(BaseProducer baseProducer) {
		this.baseProducer = baseProducer;
	}

	@Override
	public String get() {
		return baseProducer.randomElement("2", "4") + baseProducer.numerify("########");
	}
}
