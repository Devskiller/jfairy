package eu.codearte.fairyland

import static eu.codearte.fairyland.Hook.director

// 1.

println director().person().firstName()
// Alice
println director().person().firstName()
// Bob

// 2
println director().person().fistName()
//Alice
println director().person().firstName()
//Alice surname