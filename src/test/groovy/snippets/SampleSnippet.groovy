package snippets

import eu.codearte.fairyland.Fairy
import eu.codearte.fairyland.producer.text.StringifyUtil

import static eu.codearte.fairyland.Fairy.create

println "\n * With context"
3.times {
    def person = create().person()

    println "${person.fullName()} <${person.email()}>"

}

// Print word
println create().text().word(3)
println create().text().sentence(5)

def fairy = Fairy.create()
println fairy.person().firstName();
println fairy.person().firstName();

def person = fairy.person()
println person.firstName();
println person.sex();
println person.isMale();
println person.isFemale();

def text = create().text()
println text.numerify("dupa###")
println text.letterify("letter???")
println text.bothify("?? ###-###")