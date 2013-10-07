package eu.codearte.fairyland

import static eu.codearte.fairyland.Hook.create

20.times {
def person = create().person()
  println person.email() + " " + person.fullName()
}