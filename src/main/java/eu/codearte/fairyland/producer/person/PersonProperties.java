package eu.codearte.fairyland.producer.person;

import eu.codearte.fairyland.producer.BaseProducer;
import eu.codearte.fairyland.producer.Company;

import javax.inject.Inject;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-16
 */
public final class PersonProperties {

	private PersonProperties() {
	}

	public abstract static class PersonProperty {

		@Inject
		private BaseProducer baseProducer;

		public abstract void apply(Person person);

		protected BaseProducer getBaseProducer() {
			return baseProducer;
		}
	}

	public static PersonProperty male() {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.setSex(Person.Sex.MALE);
			}
		};
	}

	public static PersonProperty female() {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.setSex(Person.Sex.FEMALE);
			}
		};
	}

	public static PersonProperty minAge(final int minAge) {
		return new PersonProperty() {
			@Override
			public void apply(Person person) {
				person.setAge(getBaseProducer().randomBetween(minAge, Person.MAX_AGE));
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
