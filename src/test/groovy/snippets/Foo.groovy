package snippets

import eu.codearte.jfairy.Fairy

/**
 * @author Mariusz Smykuła
 */
class Foo {

    public static void main(String[] args) {
        def person = Fairy.create(Locale.default).person();

        println person.address
        println person.address.postalCode
    }

}
