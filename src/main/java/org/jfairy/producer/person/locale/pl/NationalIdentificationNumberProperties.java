package org.jfairy.producer.person.locale.pl;

import org.jfairy.producer.person.Person;
import org.joda.time.DateTime;

public final class NationalIdentificationNumberProperties {

	private NationalIdentificationNumberProperties() {
	}

	public abstract static class Property {

		public abstract void apply(PeselProvider nationalIdentificationNumberProvider);
	}

	public static Property dateOfBirth(final DateTime dateOfBirth) {
		return new Property() {
			@Override
			public void apply(PeselProvider nationalIdentificationNumberProvider) {
				nationalIdentificationNumberProvider.setIssueDate(dateOfBirth);
			}
		};
	}

	public static Property sex(final Person.Sex sex) {
		return new Property() {
			@Override
			public void apply(PeselProvider nationalIdentificationNumberProvider) {
				nationalIdentificationNumberProvider.setSex(sex);
			}
		};
	}

}
