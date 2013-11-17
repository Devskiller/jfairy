package snippets

import eu.codearte.fairyland.Fairy
import eu.codearte.fairyland.producer.Company
import eu.codearte.fairyland.producer.person.PersonProperties

import static eu.codearte.fairyland.Fairy.create
import static eu.codearte.fairyland.producer.person.PersonProperties.company


def c = create().company()

def person = create().person(company(c))

println person.companyEmail()

