package snippets

import eu.codearte.jfairy.Fairy

def text = Fairy.create().textProducer()

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
println text.limitedTo(10).paragraph(190)

println "* Random string *"
println text.randomString(10)
println text.randomString(20)

