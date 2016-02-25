package io.codearte.jfairy.producer.person;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.company.Company;

import java.util.Optional;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-16
 */
public final class PersonProperties {

	private static Optional<Integer> minimumAge = Optional.empty();
	private static Optional<Integer> maximumAge = Optional.empty();

	private PersonProperties() {
	}

	public abstract static class PersonProperty {

		public abstract void apply(PersonProvider person, BaseProducer baseProducer);

	}

	public static PersonProperty male() {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person, BaseProducer baseProducer) {
				person.setSex(Person.Sex.MALE);
			}
		};
	}

	public static PersonProperty female() {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person, BaseProducer baseProducer) {
				person.setSex(Person.Sex.FEMALE);
			}
		};
	}

	public static PersonProperty ageBetween(final int minAge, final int maxAge) {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person, BaseProducer baseProducer) {
				person.setAge(baseProducer.randomBetween(minAge, maxAge));
				minimumAge = Optional.of(minAge);
				maximumAge = Optional.of(maxAge);
			}
		};
	}

	public static PersonProperty minAge(final int minAge) {
		minimumAge = Optional.of(minAge);
		return ageBetween(minAge, maximumAge.orElse(PersonProvider.MAX_AGE));
	}

	public static PersonProperty maxAge(final int maxAge) {
		maximumAge = Optional.of(maxAge);
		return ageBetween(minimumAge.orElse(PersonProvider.MIN_AGE), maxAge);
	}

	public static PersonProperty telephoneFormat(final String telephoneFormat) {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person, BaseProducer baseProducer) {
				person.telephoneNumberFormat(telephoneFormat);
			}
		};
	}

	public static PersonProperty withCompany(final Company company) {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider personProvider, BaseProducer baseProducer) {
				personProvider.setCompany(company);
			}
		};
	}

}
