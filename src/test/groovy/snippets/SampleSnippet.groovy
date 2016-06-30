package snippets

import io.codearte.jfairy.Fairy
import io.codearte.jfairy.producer.person.PersonProperties

println "\n * With context"
def fairy = Fairy.create(Locale.forLanguageTag("en"))
3.times {
	def person = fairy.person()

	println "${person.getFullName()} <${person.getEmail()}>"

}

// Print word
println fairy.textProducer().word(3)
println fairy.textProducer().sentence(5)

println fairy.person().getFirstName()
println fairy.person().getFirstName()

def person = fairy.person()
println ""
println "         First name: " + person.getFirstName()
println "        Middle name: " + person.getMiddleName()
println "          Last name: " + person.getLastName()
println "           Username: " + person.getUsername()
println "           Password: " + person.getPassword()
println "             isMale: " + person.isMale()
println "           isFemale: " + person.isFemale()
println "              Phone: " + person.getTelephoneNumber()
println "      Date of birth: " + person.getDateOfBirth()
println "                Age: " + person.getAge()
println "     National Id No: " + person.getNationalIdentificationNumber()
println "National Id Card No: " + person.getNationalIdentityCardNumber()
println "        Passport No: " + person.getPassportNumber()
println "        Postal Code: " + person.address.postalCode
println "               City: " + person.address.city
println "             Street: " + person.address.street()
println "      Street number: " + person.address.streetNumber()
println "       Full address: " + person.address.toString()

println ""

3.times {
	println "  Men: " + fairy.person(PersonProperties.male()).getFullName()
}

3.times {
	println "Women: " + fairy.person(PersonProperties.female()).getFullName()
}

println fairy.person().getNationalIdentityCardNumber()
println fairy.person().getNationalIdentificationNumber()

def company = fairy.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()
company = fairy.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()
