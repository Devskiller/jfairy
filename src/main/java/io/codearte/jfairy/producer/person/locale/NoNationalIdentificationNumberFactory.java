package io.codearte.jfairy.producer.person.locale;

import com.google.inject.assistedinject.Assisted;
import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.person.*;
import org.joda.time.DateTime;

import javax.inject.Inject;

public class NoNationalIdentificationNumberFactory implements NationalIdentificationNumberFactory {

	@Inject
	public NoNationalIdentificationNumberFactory(BaseProducer baseProducer, DateProducer dateProducer) {
	}

	@Override
	public NoNationalIdentificationNumberProvider produceNationalIdentificationNumberProvider(NationalIdentificationNumberProperties.Property... properties) {
		return new NoNationalIdentificationNumberProvider(null, null, properties);
	}

	public static class NoNationalIdentificationNumberProvider implements NationalIdentificationNumberProvider {


		@Inject
		public NoNationalIdentificationNumberProvider(DateProducer dateProducer, BaseProducer baseProducer,
													  @Assisted NationalIdentificationNumberProperties.Property... properties) {
		}

		@Override
		public NationalIdentificationNumber get() {
			return new NationalIdentificationNumber("");
		}

		@Override
		public void setIssueDate(DateTime dateOfBirth) {
		}

		@Override
		public void setSex(Person.Sex sex) {
		}

	}

}
