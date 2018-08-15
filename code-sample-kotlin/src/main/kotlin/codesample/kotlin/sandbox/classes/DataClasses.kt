package codesample.kotlin.sandbox.classes

data class Dude(val name: String,
                val surname: String,
                val age: Int) {
    val someProp : String
        get() = "getter is called!"
}

/** Run this func in test BasicsRunner */
fun dataClasses() {
    val ivan = Dude("Ivan", "Ivanov", 15)
    println(ivan.toString() + " ivans hash: " + ivan.hashCode())

    /* This may seem like accessing a public field... but it's not! This access actually
    * call a getter on some prop */
    println(ivan.someProp)
}