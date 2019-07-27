// BinaryTree.java
//
// By Sebastian Raaphorst, 2019.

package dcp.day112;

import java.util.*;

// A binary tree with a parent reference.
public class BinaryTree<T> {
    // For the root, this is None.
    private Optional<BinaryTree<T>> parent;
    private Optional<BinaryTree<T>> left;
    private Optional<BinaryTree<T>> right;
    final private T value;

    public BinaryTree(final T value) {
        this.value = value;
        parent = Optional.empty();
        left   = Optional.empty();
        right  = Optional.empty();
    }

    public void setLeft(final BinaryTree<T> left) {
        this.left   = Optional.of(left);
        left.parent = Optional.of(this);
    }

    public final Optional<BinaryTree<T>> getLeft() {
        return left;
    }

    public void setRight(final BinaryTree<T> right) {
        this.right   = Optional.of(right);
        right.parent = Optional.of(this);
    }

    public final Optional<BinaryTree<T>> getRight() {
        return right;
    }

    public final Optional<BinaryTree<T>> getParent() {
        return parent;
    }

    public boolean isRoot() {
        return parent.isEmpty();
    }

    public int getDepth() {
        return isRoot() ? 0 : 1 + parent.map(BinaryTree::getDepth).orElse(0);
    }
    /**
     * Unlink the pointers to remove loops so the GC can handle this.
     */
    public void destroy() {
        parent = Optional.empty();
        left.ifPresent(BinaryTree::destroy);
        right.ifPresent(BinaryTree::destroy);
    }

    /**
     * Get a path indexed by depth from the current node.
     */
    private Map<Integer, BinaryTree<T>> pathMap() {
        var map = new HashMap<Integer, BinaryTree<T>>();
        var depth = getDepth();

        var p = this;
        for (; !p.isRoot(); p = p.parent.get()) {
            map.put(depth, p);
        }
        map.put(0, p);

        return map;
    }

    /**
     * Find the least common ancestor two nodes.
     */
    public static <T> BinaryTree<T> leastCommonAncestor(final BinaryTree<T> t1, final BinaryTree<T> t2) {
        final var m1 = t1.pathMap();
        final var m2 = t2.pathMap();

        // Find the minimum of the two maximum depths of the trees.
        final var minmax = Math.min(Collections.max(m1.keySet()), Collections.max(m2.keySet()));

        // Search from minmax to the root to find the LCA.
        for (var depth = minmax; depth >= 0; --depth) {
            final var m1node = m1.get(depth);
            final var m2node = m2.get(depth);
            if (m1node.equals(m2node))
                return m1node;
        }

        // If we made it here, there is a serious issue.
        throw new IllegalArgumentException("No least common ancestor: different trees?");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryTree)) return false;
        final var that = (BinaryTree<?>) o;
        return parent.equals(that.parent) &&
                left.equals(that.left) &&
                right.equals(that.right) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, left, right, value);
    }

    public static void main(String[] args) {
        final var root = new BinaryTree<>(0);
        final var left = new BinaryTree<>(1);
        final var right = new BinaryTree<>(2);
        root.setLeft(left);
        root.setRight(right);
        assert(leastCommonAncestor(left, right) == root);
        assert(leastCommonAncestor(left, left) == left);
        assert(leastCommonAncestor(root, root) == root);
        root.destroy();
    }
}
