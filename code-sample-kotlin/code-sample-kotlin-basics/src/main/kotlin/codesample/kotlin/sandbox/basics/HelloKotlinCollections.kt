package codesample.kotlin.sandbox.basics


fun main(args: Array<String>) {

    println("******* LISTS ********")
    val mutableList: MutableList<Int> = mutableListOf(1, 2)
    mutableList.add(3)

    val immutableList: List<Int> = mutableList

    println("Mutable list before add:")
    println(mutableList.joinToString(", "))

    println("Immutable list before add:")
    println(immutableList.joinToString(", "))

    /* Immutable view does not contain any methods to add elements HOWEVER it's still
    a reference to mutable list, so adding an element in mutable list will make it
    appear in immutable list
     */
    mutableList.add(4)
    println("Mutable list after add:")
    println(mutableList.joinToString(", "))
    println("Immutable list after add:")
    println(immutableList.joinToString(", "))


    println("\n******* MAPS ********")
    val map = hashMapOf("foo" to 1, "bar" to 2, "fizz" to 3, "buzz" to 4)
    map["kek"] = 6

    println("map keys and values")
    map.entries.forEach { println("${it.key} -> ${it.value}") }
    println("\nmap keys starts with f")
    map.filterKeys { it.startsWith('f') }
            .forEach { key, value -> println("$key -> $value") }


    /* Create an uneatable map from expression*/
    val myMap = mapOf(1 to "1", 2 to "3")
    val myMap2 = mapOf(Pair(1, "1"), Pair(2, "2"))
    println("myMap1 = $myMap")
    println("maMap2 = [$myMap2]")

    /* Traverse map by key-value pairs */
    for ( (key,value) in myMap2) {
        println("key: $key value: $value")
    }
    /* analogues to Java's myMap.get(1) */
    println(myMap[1])

    val list: List<Int>? = null
    println(list?.size ?: "list is empty!")
    list?.let {
        println("List is not null!")
    }

    // Won't print anything

    val value = MySingleTonOne.value?.value ?: "default str value"
    println(value)


    // The difference is that first one does not have a return value (return value is Unit) and second one DO
    // have a return value of MySingleTonThree (because you take it and then apply 3 functions TO it)
    with(MySingleTonThree) {
        init()
        doWork()
        close()
    }
    val result = MySingleTonThree.apply {
        init()
        doWork()
        close()
    }

    MySingleTonThree.also {
        it.init()
        it.doWork()
        it.close()
    }
}

object MySingleTonOne {
    val value: MySingleTonTwo? = null
}

object MySingleTonTwo {
    val value: String? = null
}

object MySingleTonThree {
    fun init(): Unit {
        println("init...")
    }

    fun doWork(): Unit {
        println("do work...")
    }

    fun close(): Unit {
        println("close...")
    }
}