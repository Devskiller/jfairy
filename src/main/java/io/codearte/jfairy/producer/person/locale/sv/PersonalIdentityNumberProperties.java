package io.codearte.jfairy.producer.person.locale.sv;

import io.codearte.jfairy.producer.person.Person;
import org.joda.time.DateTime;

public final class PersonalIdentityNumberProperties {

	private PersonalIdentityNumberProperties() {
	}

	public abstract static class Property {

		public abstract void apply(PersonalIdentityNumberProvider personalIdentityNumberProvider);
	}

	public static Property dateOfBirth(final DateTime dateOfBirth) {
		return new Property() {
			@Override
			public void apply(PersonalIdentityNumberProvider personalIdentityNumberProvider) {
				personalIdentityNumberProvider.setIssueDate(dateOfBirth);
			}
		};
	}

	public static Property sex(final Person.Sex sex) {
		return new Property() {
			@Override
			public void apply(PersonalIdentityNumberProvider personalIdentityNumberProvider) {
				personalIdentityNumberProvider.setSex(sex);
			}
		};
	}

}
