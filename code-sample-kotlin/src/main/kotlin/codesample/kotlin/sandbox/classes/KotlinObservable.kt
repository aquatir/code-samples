package codesample.kotlin.sandbox.classes

import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("Default") {
        prop, old, new -> println("$old -> $new")
    }
}

/** Run this func in test BasicsRunner */
fun kotlinObservable() {
    val usr = User()
    usr.name = "Ivan"
    usr.name = "Narkoman"
}