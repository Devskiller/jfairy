package eu.codearte.fairyland.producer.person;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-11-16
 */
public class PersonProperties {

	public interface PersonProperty {
		void apply();

	}

	public static PersonProperty male() {
		return new PersonProperty() {
			@Override
			public void apply() {

			}
		};
	}

	public static PersonProperty female() {
		return new PersonProperty() {
			@Override
			public void apply() {

			}
		};
	}

	public static PersonProperty minAge(final int minAge) {
		return new PersonProperty() {
			@Override
			public void apply() {

			}
		};
	}
}
