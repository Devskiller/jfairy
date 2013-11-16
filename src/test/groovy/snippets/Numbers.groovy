package snippets

import eu.codearte.fairyland.producer.locale.pl.NIP
import eu.codearte.fairyland.producer.person.locale.pl.PolishIdentityCardNumber
import eu.codearte.fairyland.producer.util.DateGenerator
import eu.codearte.fairyland.producer.util.RandomGenerator
import eu.codearte.fairyland.producer.util.TimeProvider
import org.joda.time.DateTime

def random = new RandomGenerator(3L)
def dateGenerator = new DateGenerator(random, new TimeProvider())

def dowod = new PolishIdentityCardNumber(dateGenerator, random);
def nip = new NIP(random)

println dowod.generate(DateTime.now())

println nip.generate()
