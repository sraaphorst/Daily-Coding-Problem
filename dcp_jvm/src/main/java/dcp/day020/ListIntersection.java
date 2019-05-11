package dcp.day020;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Given two singly linked lists, find their intersection point, where the intersection point is referential.
 */
final public class ListIntersection {
    /**
     * We want equals
     * @param <T>
     */
    final public static class Node<T> {
        private final T value;
        private Optional<Node<T>> next;

        public Node(final T value) {
            this.value = value;
            this.next  = Optional.empty();
        }

        public Optional<Node<T>> getNext() {
            return next;
        }

        // Convenience method to link in a chain.
        public Node<T> setNext(final Node<T> next) {
            this.next = Optional.of(Objects.requireNonNull(next));
            return next;
        }

        public void setNext(final Optional<Node<T>> next) {
            this.next = Objects.requireNonNull(next);
        }

        public T getValue() {
            return value;
        }
    }

    public static <T> Optional<Node<T>> findIntersectionPoint(final Node<T> n1, final Node<T> n2) {
        final Set<Node<T>> visited = new LinkedHashSet<>();

        var on = Optional.of(n1);
        while (on.isPresent()) {
            final Node<T> n = on.get();
            visited.add(n);
            on = n.getNext();
        }

        on = Optional.of(n2);
        while (on.isPresent()) {
            final Node<T> n = on.get();
            if (visited.contains(n))
                return on;
            on = n.getNext();
        }

        return Optional.empty();
    }
}
