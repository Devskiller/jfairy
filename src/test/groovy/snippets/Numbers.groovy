package snippets

import eu.codearte.fairyland.producer.RandomGenerator
import eu.codearte.fairyland.producer.person.pl.NIP
import eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber

def random = new RandomGenerator(3L)
def dowod = new PolishIdentityCardNumber(random);
def nip = new NIP(random)

println dowod.generate(new GregorianCalendar())


println nip.generate()