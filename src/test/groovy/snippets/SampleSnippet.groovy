package snippets

import eu.codearte.fairyland.Fairy

import static eu.codearte.fairyland.Fairy.create

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

println ""

3.times {
  println "  Men: " + person.male().fullName()
}

3.times {
  println "Women: " + person.female().fullName()
}

println Fairy.create().numerify("bleble###")
println Fairy.create().letterify("letter???")
println Fairy.create().bothify("?? ###-###")

println fairy.nationalIdentityNumber()
println fairy.nationalIdentificationNumber()

def create = Fairy.create()
def company = create.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()
company = create.company()
println "Company: " + company.name()
println "URL: " + company.url()
println "VAT: " + company.vatIdentificationNumber()