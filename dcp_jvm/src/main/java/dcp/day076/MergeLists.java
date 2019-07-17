package dcp.day076;

// We will use SinglyLinkedList from day 73 for this problem.

import dcp.day073.SingleLinkedList;

public class MergeLists {
    static class ListNode<T extends Comparable<T>> {
        final T data;
        ListNode<T> next;

        ListNode(final T data, ListNode<T> next) {
            this.data = data;
            this.next = next;
        }

        ListNode(final T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Continuously merge two lists until we only have one list left, which is the answer.
    public static <T extends Comparable<T>> ListNode<T> mergeTwo(ListNode<T> list1, ListNode<T> list2) {
        ListNode<T> header = null;

        while (list1 != null || list2 != null) {
            ListNode<T> prev = null;
            ListNode<T> next = null;
            if (list1 == null) {
                next = list2;
                list2 = list2.next;
            } else if (list2 == null) {
                next = list1;
                list1 = list1.next;
            } else if (list1.data.compareTo(list2.data) <= 0) {
                next = list1;
                list1 = list1.next;
            } else {
                next = list2;
                list2 = list2.next;
            }

            if (header == null)
                header = next;
            else {
                prev.next = next;
                prev = next;
            }
        }

        return header;
    }
}
