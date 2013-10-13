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
println text.sentence(190)
println "* Paragraph *"
println text.paragraph()
println text.paragraph(190)

