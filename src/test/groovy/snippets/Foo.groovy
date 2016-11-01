package snippets

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.Person

/**
 * @author Mariusz Smykuła
 */
class Foo {

	public static void main(String[] args) {
		Person person = Fairy.create(Locale.default).person();

		println person.address
		println person.address.postalCode
	}

}
