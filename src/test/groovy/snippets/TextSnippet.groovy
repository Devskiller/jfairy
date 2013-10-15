package snippets

import eu.codearte.fairyland.Fairy
def text = Fairy.create().text()

println "* Lorem ipsum *"
println text.loremIpsum()

println "* Words *"
println text.word(1)
println text.word()
println text.word(190)

println "* Sentences *"
println text.sentence()
println text.sentence(3)
println text.sentence(190)

println "* Paragraph *"
println text.paragraph()
println text.paragraph(190)
println text.limit(10).paragraph(190)

