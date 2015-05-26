package io.codearte.jfairy.producer.person;

import io.codearte.jfairy.producer.BaseProducer;
import io.codearte.jfairy.producer.company.Company;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-16
 */
public final class PersonProperties {

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

	public static PersonProperty minAge(final int minAge) {
		return new PersonProperty() {
			@Override
			public void apply(PersonProvider person, BaseProducer baseProducer) {
				person.setAge(baseProducer.randomBetween(minAge, PersonProvider.MAX_AGE));
			}
		};
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
