package io.codearte.jfairy.dataProvider.person;

import io.codearte.jfairy.dataProvider.BaseProducer;
import io.codearte.jfairy.dataProvider.company.Company;

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

		public abstract void apply(PersonProvider person);

		protected BaseProducer getBaseProducer() {
			return baseProducer;
		}
	}

	public static PersonProperty male() {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person) {
				person.setSex(Person.Sex.MALE);
			}
		};
	}

	public static PersonProperty female() {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person) {
				person.setSex(Person.Sex.FEMALE);
			}
		};
	}

	public static PersonProperty minAge(final int minAge) {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person) {
				person.setAge(getBaseProducer().randomBetween(minAge, PersonProvider.MAX_AGE));
			}
		};
	}

	public static PersonProperty telephoneFormat(final String telephoneFormat) {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person) {
				person.telephoneNumberFormat(telephoneFormat);
			}
		};
	}

	public static PersonProperty withCompany(final Company company) {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider personProvider) {
				personProvider.setCompany(company);
			}
		};
	}

}
