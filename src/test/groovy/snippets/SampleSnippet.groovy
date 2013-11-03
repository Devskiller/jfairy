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
println person.firstName();
println person.isMale();
println person.isFemale();
println person.telephoneNumber();
println person.dateOfBirth();
println person.age();



println Fairy.create().numerify("bleble###")
println Fairy.create().letterify("letter???")
println Fairy.create().bothify("?? ###-###")

println fairy.nationalIdentityNumber()
println fairy.nationalIdentificationNumber()