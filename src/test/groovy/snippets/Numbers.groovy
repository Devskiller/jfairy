package snippets

import eu.codearte.fairyland.producer.RandomGenerator
import eu.codearte.fairyland.producer.person.pl.PolishIdentityCardNumber

def dowod = new PolishIdentityCardNumber(new RandomGenerator(3L));

println dowod.identityNumber(2013)
println dowod.identityNumber(2013)
println dowod.identityNumber(2015)