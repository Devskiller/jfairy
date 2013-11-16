package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.Company;
import eu.codearte.fairyland.producer.util.RandomGenerator;

import javax.inject.Inject;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-16
 */
public class PersonProperties {

	public abstract static class PersonProperty {

		@Inject
		private RandomGenerator randomGenerator;

		public abstract void apply(Person person);

		protected RandomGenerator getRandomGenerator() {
			return randomGenerator;
		}
	}

	public static PersonProperty male() {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.setSex(Sex.MALE);
			}
		};
	}

	public static PersonProperty female() {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.setSex(Sex.FEMALE);
			}
		};
	}

	public static PersonProperty minAge(final int minAge) {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.setAge(getRandomGenerator().randomBetween(minAge, 100));
			}
		};
	}

	public static PersonProperty telephoneFormat(final String telephoneFormat) {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.telephoneNumberFormat(telephoneFormat);
			}
		};
	}

	public static PersonProperty withCompany(final Company company) {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.setCompany(company);
			}
		};
	}
}
