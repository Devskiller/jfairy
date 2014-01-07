package snippets

import org.jfairy.Fairy
import org.jfairy.producer.person.PersonProperties

println "\n * With context"
def fairy = Fairy.create(Locale.forLanguageTag("en"))
3.times {
	def person = fairy.person()

	println "${person.fullName()} <${person.email()}>"

}

// Print word
println fairy.text().word(3)
println fairy.text().sentence(5)

println fairy.person().firstName();
println fairy.person().firstName();

def person = fairy.person()
println "   First name: " + person.firstName();
println "   Middle name: " + person.middleName();
println "    Last name: " + person.lastName();
println "     Username: " + person.username();
println "     Password: " + person.password();
println "       isMale: " + person.isMale();
println "     isFemale: " + person.isFemale();
println "        Phone: " + person.telephoneNumber();
println "Date of birth: " + person.dateOfBirth();
println "          Age: " + person.age();
println "        Pesel: " + person.nationalIdentificationNumber();
println "           ID: " + person.nationalIdentityCardNumber();
println "  Postal Code: " + person.getAddress().postalCode();
println "  		  City: " + person.getAddress().city();

println ""

3.times {
	println "  Men: " + fairy.person(PersonProperties.male()).fullName()
}

3.times {
	println "Women: " + fairy.person(PersonProperties.female()).fullName()
}

println fairy.person().nationalIdentityCardNumber()
println fairy.person().nationalIdentificationNumber()

def company = fairy.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()
company = fairy.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()