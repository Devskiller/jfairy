package snippets

import eu.codearte.fairyland.producer.util.RandomGenerator
import eu.codearte.fairyland.producer.person.pl.NIP
import eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber
import org.joda.time.DateTime

def random = new RandomGenerator(3L)
def dowod = new PolishIdentityCardNumber(random);
def nip = new NIP(random)

println dowod.generate(DateTime.now())

println nip.generate()
