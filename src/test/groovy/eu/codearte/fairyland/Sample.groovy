package eu.codearte.fairyland

import static eu.codearte.fairyland.Hook.anyPerson
import static eu.codearte.fairyland.Hook.create

println "\n * With context"
3.times {
    def person = create().person()

    println "${person.fullName()} <${person.email()}>"

}

println "\n * Without context"
3.times {
    println "${anyPerson().fullName()} <${anyPerson().email()}>"

}