// FileStructure.java
//
// By Sebastian Raaphorst, 2019.

package dcp.day059;

import java.security.MessageDigest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class FileStructure {
    /**
     * Generic superclass that is a node in the file structure.
     */
    public abstract static class MerkleTreeNode {
        final UUID identifier;
        final MessageDigest md;
        byte[] digest;
        final MerkleTreeNode parent;
        Instant timestamp;

        MerkleTreeNode(final UUID identifier, final MessageDigest md, final MerkleTreeNode parent) {
            this.identifier = identifier;
            this.md = md;
            this.parent = parent;
            if (!parent.isDirectory())
                throw new IllegalArgumentException("parent must always be a directory");
            timestamp = Instant.now();
        }

        abstract void recalculateDigest();

        byte[] getDigest() {
            return digest;
        }

        Instant getTimestamp() {
            return timestamp;
        }

        boolean isFile() { return false; }
        boolean isDirectory() { return false; }
        boolean isRoot() { return false; }

        @Override public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (!(obj instanceof MerkleTreeNode))
                return false;
            return Arrays.equals(digest, ((MerkleTreeNode)obj).digest);
        }
    }

    /**
     * A node representing a file in the file structure.
     */
    public static class MerkleTreeFile extends MerkleTreeNode {
        private byte[] contents;

        protected MerkleTreeFile(final UUID identifier, final MessageDigest md, final MerkleTreeNode parent) {
            super(identifier, md, parent);
        }

        public byte[] getContents() {
            return contents;
        }

        public void setContents(final byte[] contents) {
            this.contents = contents;
            recalculateDigest();
        }

        @Override void recalculateDigest() {
            md.reset();
            md.update(contents);
            digest = md.digest();
        }

        @Override boolean isFile() {
            return true;
        }
    }

    /**
     * A node representing a directory in the file structure.
     */
    public static class MerkleTreeDirectory extends MerkleTreeNode {
        // Subnodes can be other directories or files.
        private final ArrayList<MerkleTreeNode> subnodes;

        protected MerkleTreeDirectory(final UUID identifier, final MessageDigest md, final MerkleTreeNode parent) {
            super(identifier, md, parent);
            subnodes = new ArrayList<>();
        }

        void addNode(final MerkleTreeNode node) {
            if (subnodes.contains(node))
                return;
            subnodes.add(node);
            recalculateDigest();
        }

        void removeNode(final MerkleTreeNode node) {
            if (!subnodes.contains(node))
                return;
            subnodes.remove(node);
            recalculateDigest();
        }

        final ArrayList<MerkleTreeNode> getSubnodes() {
            return subnodes;
        }

        @Override void recalculateDigest() {
            md.reset();
            subnodes.forEach(n -> md.update(n.digest));
            digest = md.digest();
        }

        @Override boolean isDirectory() {
            return true;
        }
    }

    /**
     * The root directory. Not thrilled with this architecture, as would not like to have null as parent.
     */
    public static class MerkleTreeRootDirectory extends MerkleTreeDirectory {
        protected MerkleTreeRootDirectory(final UUID identifier, final MessageDigest md) {
            super(identifier, md, null);
        }

        @Override boolean isRoot() {
            return true;
        }
    }


    private MerkleTreeRootDirectory root;

    public FileStructure(final MerkleTreeRootDirectory root) {
        this.root = root;
    }

    private static void compare(final MerkleTreeDirectory d1,
                                final MerkleTreeDirectory d2,
                                final ArrayList<MerkleTreeNode> changes) {
        if (Arrays.compare(d1.digest, d2.digest) == 0)
            return;

        // Otherwise, get the subcontents and compare.
        final ArrayList<MerkleTreeNode> n1 = d1.getSubnodes();
        final ArrayList<MerkleTreeNode> n2 = d2.getSubnodes();

        // Remove the things in n1 that are in n2 and have the same digest as in n2, and vice versa..
        for (final var nn1: n1)
            n2.removeIf(nn2 -> nn2.identifier.equals(nn1.identifier) && Arrays.compare(nn1.digest, nn2.digest) == 0);
        for (final var nn2: n2)
            n1.removeIf(nn1 -> nn1.identifier.equals(nn2.identifier) && Arrays.compare(nn1.digest, nn2.digest) == 0);

        // Match the things to process.
        // If two things don't have same UUID, add them.
        // If two things do have same UUID, process them.
        while (!(n1.isEmpty() && n2.isEmpty())) {
            if (n1.isEmpty()) {
                changes.addAll(n2);
                break;
            }
            if (n2.isEmpty()) {
                changes.addAll(n1);
            }

            final var p1 = n1.get(0);
            final var p2 = n2.stream().filter(nn2 -> p1.identifier.equals(nn2.identifier)).findFirst();
            if (p1 instanceof MerkleTreeFile) {
                changes.add(p1);
                n1.remove(p1);
                p2.ifPresent(pp2 -> {
                    changes.add(pp2);
                    n2.remove(pp2);
                });
            } else {
                final MerkleTreeDirectory dd1 = (MerkleTreeDirectory) p1;
                p2.filter(x -> x instanceof MerkleTreeDirectory)
                        .map(x -> (MerkleTreeDirectory) x)
                        .ifPresent(dd2 -> compare(dd1, dd2, changes));
            }
        }
    }

    public ArrayList<MerkleTreeNode> calculateSync(final FileStructure other) {
        if (!root.identifier.equals((other.root.identifier)))
            throw new IllegalArgumentException("Cannot compare different file systems.");
        final ArrayList<MerkleTreeNode> changes = new ArrayList<>();
        compare(root, other.root, changes);
        return changes;
    }
}
