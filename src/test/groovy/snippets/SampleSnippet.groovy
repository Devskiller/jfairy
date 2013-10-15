package snippets

import static eu.codearte.fairyland.Fairy.create

println "\n * With context"
3.times {
    def person = create().person()

    println "${person.fullName()} <${person.email()}>"

}

// Print word
println create().text().word(3)
println create().text().sentence(5)
