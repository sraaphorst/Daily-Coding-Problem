// SingleLinkedList.java
//
// By Sebastian Raaphorst, 2019.

package dcp.day073;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

/**
 * A single linked list that can be reversed in place.
 * @param <T> the type of the list
 */
final public class SingleLinkedList<T> implements Iterable<T> {
    /**
     * A node in the list.
     * @param <T> the type of the data held in the node
     */
    private static class Node<T> {
        T data;
        Optional<Node<T>> tail;

        /**
         * Create a node and append a tail to it.
         * @param data the data in the node
         * @param tail the tail to append to the node
         */
        Node(T data, Optional<Node<T>> tail) {
            this.data = Objects.requireNonNull(data);
            this.tail = Objects.requireNonNull(tail);
        }

        /**
         * Ctrate a single node containing data and no tail.
         * @param data the data in the node
         */
        Node(T data) {
            this(data, Optional.empty());
        }
    }

    /**
     * Convenience method to create a populated Optional of a node.
     * @param data the data in the node
     * @param tail the tail to append to the node
     * @param <T> the type of the data held in the node
     * @return a populated Optional holding a node with this data
     */
    private static <T> Optional<Node<T>> create(T data, Optional<Node<T>> tail) {
        return Optional.of(new Node<>(data, tail));
    }

    /**
     * Convenience method to create a populated Optional of a node.
     * @param data the data in the node
     * @param <T> the type of the data held in the node
     * @return a poulated Optional holding a node with data
     */
    private static <T> Optional<Node<T>> create(T data) {
        return Optional.of(new Node<>(data));
    }

    /**
     * Allow iteration over the list.
     * @param <T> the type of the data held in the list
     */
    private static class NodeIterator<T> implements Iterator<T> {
        Optional<Node<T>> node;

        NodeIterator(Optional<Node<T>> head) {
            node = Objects.requireNonNull(head);
        }

        @Override
        public boolean hasNext() {
            return node.isPresent();
        }

        @Override
        public T next() {
            var data = node.map(p -> p.data);
            node = node.flatMap(p -> p.tail);
            return data.get();
        }
    }

    private Optional<Node<T>> head;

    public SingleLinkedList() {
        head = Optional.empty();
    }

    /**
     * Append an element to the singly-linked list.
     * Note this is a slow, O(n) operation as we must traverse the whole list.
     * @param data the element to append
     * @return the list, to allow chaining operations
     */
    public SingleLinkedList<T> append(T data) {
        if (head.isEmpty())
            head = create(data);
        else {
            // Go to the last element
            var ptr = head;
            while (ptr.isPresent() && ptr.get().tail.isPresent())
                ptr = ptr.flatMap(p -> p.tail);
            ptr.ifPresent(p -> p.tail = create(data));
        }
        return this;
    }

    /**
     * Prepend an element to the singly-linked list in O(1).
     * @param data the element to prepend
     * @return the list, to allow chaining operations
     */
    public SingleLinkedList<T> prepend(T data) {
        head = create(data, head);
        return this;
    }

    private void reverse_aux(Optional<Node<T>> prev, Optional<Node<T>> current) {
        // If we have reached the end, make the head the last element.
        if (current.isEmpty())
            head = prev;

        // Otherwise, continue and then reverse.
        else {
            reverse_aux(current, current.flatMap(p -> p.tail));
            current.ifPresent(p -> p.tail = prev);
        }
    }

    /**
     * Reverse the contents of the list by traversing the list and then backtracking, flipping the pointers.
     */
    public void reverse() {
        reverse_aux(Optional.empty(), head);
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(head);
    }
}
