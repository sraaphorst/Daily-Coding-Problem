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

    private static <T> Optional<Node<T>> create(T data, Optional<Node<T>> tail) {
        return Optional.of(new Node<>(data, tail));
    }

    /**
     * Convenience method to create a populated Option of a node.
     * @param data the data in the node
     * @param <T> the type of the data held in the node
     * @return a poulated Option holding a node with data
     */
    private static <T> Optional<Node<T>> create(T data) {
        return Optional.of(new Node<>(data));
    }

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

    private int size;
    private Optional<Node<T>> head;

    public SingleLinkedList() {
        size = 0;
        head = Optional.empty();
    }

    public SingleLinkedList<T> append(T data) {
        if (head.isEmpty()) {
            head = create(data);
            size = 1;
        }
        else {
            var ptr = head;
            while (ptr.isPresent() && ptr.get().tail.isPresent())
                ptr = ptr.flatMap(p -> p.tail);
            ptr.ifPresent(p -> p.tail = create(data));
            ++size;
        }
        return this;
    }

    public SingleLinkedList<T> prepend(T data) {
        head = create(data, head);
        ++size;
        return this;
    }

    private void reverse_aux(Optional<Node<T>> prev, Optional<Node<T>> current) {
        // If we have reached the end, make the head the last element.
        if (current.isEmpty()) {
            head = prev;
        }

        // Otherwise, continue and then reverse.
        else {
            reverse_aux(current, current.flatMap(p -> p.tail));
            current.ifPresent(p -> p.tail = prev);
        }
    }

    public void reverse() {
        reverse_aux(Optional.empty(), head);
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(head);
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> sll1 = new SingleLinkedList<>();
        sll1.prepend(1).prepend(2).prepend(3).prepend(4).prepend(5);
        for (final var i: sll1) {
            System.out.println(i);
        }

        sll1.reverse();
        for (final var i: sll1) {
            System.out.println(i);
        }

    }
}
