package snippets

import org.jfairy.Fairy
import org.jfairy.producer.person.PersonProperties

import static org.jfairy.Fairy.create

println "\n * With context"
def fairy = create(Locale.forLanguageTag("pl"))
3.times {
	def person = fairy.person()

	println "${person.fullName()} <${person.email()}>"

}

// Print word
println create().text().word(3)
println create().text().sentence(5)

println fairy.person().firstName();
println fairy.person().firstName();

def person = fairy.person()
println "   First name: " + person.firstName();
println "    Last name: " + person.lastName();
println "       isMale: " + person.isMale();
println "     isFemale: " + person.isFemale();
println "        Phone: " + person.telephoneNumber();
println "Date of birth: " + person.dateOfBirth();
println "          Age: " + person.age();
println "        Pesel: " + person.nationalIdentificationNumber();
println "           ID: " + person.nationalIdentityCardNumber();
println "  Postal Code: " + person.getAddress().postalCode();
println "  				City: " + person.getAddress().city();

println ""

3.times {
	println "  Men: " + fairy.person(PersonProperties.male()).fullName()
}

3.times {
	println "Women: " + fairy.person(PersonProperties.female()).fullName()
}

println fairy.person().nationalIdentityCardNumber()
println fairy.person().nationalIdentificationNumber()

def create = Fairy.create()
def company = create.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()
company = create.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()