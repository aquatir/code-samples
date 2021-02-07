import com.codesample.datastructs.basic.Node
import java.lang.RuntimeException
import java.lang.StringBuilder

interface Queue<T> {
    fun enqueue(value: T)
    fun dequeue(): T
}

class QueueOnDoublyLinkedList<T> : Queue<T> {

    var head: Node<T>? = null
    var tail: Node<T>? = null

    override fun enqueue(value: T) {
        if (tail == null) {
            tail = Node(value, null)
            head = tail
        } else {
            val newTail = Node(value, null)
            tail!!.next = newTail
            tail = newTail
        }
    }

    override fun dequeue(): T {
        return if (head == null) throw RuntimeException("No elements to dequeue")
        else {
            val oldHead = head
            val value = head!!.value
            head = head!!.next
            oldHead!!.next = null

            value
        }
    }

    override fun toString(): String {
        return when (head) {
            null -> "QueueOnDoublyLinkedList(head=null,tail=null,values=[])"
            tail -> "QueueOnDoublyLinkedList(head=${head!!.value},tail=${head!!.value},values=[${head!!.value}])"
            else -> {
                val strBuilder = StringBuilder()
                strBuilder.append("QueueOnDoublyLinkedList(head=${head!!.value},tail=${tail!!.value},values=[")
                var current = head
                while (current?.next != null) {
                    strBuilder.append("${current.value},")
                    current = current.next
                }
                strBuilder.append("${tail!!.value}])")
                strBuilder.toString()
            }
        }
    }

}

class QueueOnResizingArray<T> : Queue<T> {

    private var array: MutableList<T?> = MutableList(2) { null }
    private var curSize: Int = 0
    private var curMaxSize: Int = 2


    override fun enqueue(value: T) {
        TODO("Not yet implemented")
    }

    override fun dequeue(): T {
        TODO("Not yet implemented")
    }

}
