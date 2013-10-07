package eu.codearte.fairyland

import static eu.codearte.fairyland.Hook.director

// Case 1

println director().person().firstName()
// Alice
println director().person().firstName()
// Bob

// Case 2
println director().person().firstName()
//Alice
println director().person().lastName()
//Alice surname