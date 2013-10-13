package eu.codearte.fairyland


def text = Hook.create().text()
println text.loremIpsum
println "* Words "
println text.words(1)
println text.words()
println text.words(190)
println "* Sentences"
println text.sentence()
println text.sentence(3)
println text.sentence(5)
println text.sentence(9)
println text.sentence(15)
println text.sentence(19)
println text.sentence(190)

