package io.codearte.jfairy.producer.person;

import org.joda.time.DateTime;

public final class NationalIdentificationNumberProperties {

	private NationalIdentificationNumberProperties() {
	}

	public abstract static class Property {

		public abstract void apply(NationalIdentificationNumberProvider nationalIdentificationNumberProvider);
	}

	public static Property dateOfBirth(final DateTime dateOfBirth) {
		return new Property() {
			@Override
			public void apply(NationalIdentificationNumberProvider nationalIdentificationNumberProvider) {
				nationalIdentificationNumberProvider.setIssueDate(dateOfBirth);
			}
		};
	}

	public static Property sex(final Person.Sex sex) {
		return new Property() {
			@Override
			public void apply(NationalIdentificationNumberProvider nationalIdentificationNumberProvider) {
				nationalIdentificationNumberProvider.setSex(sex);
			}
		};
	}

}
