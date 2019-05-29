// StackQueue.java
//
// By Sebastian Raaphorst, 2019.

package dcp.day053;

import java.util.Stack;

public final class StackQueue<T> {
    private Stack<T> contents;

    public StackQueue() {
        contents = new Stack<T>();
    }

    public synchronized void enqueue(final T item) {
        contents.push(item);
    }

    public synchronized T dequeue() {
        final Stack<T> tmp = new Stack<>();
        while (!contents.empty())
            tmp.push(contents.pop());
        final T item = tmp.pop();
        while (!tmp.empty())
            contents.push(tmp.pop());

        return item;
    }

    public int size() {
        return contents.size();
    }

    public boolean empty() {
        return contents.empty();
    }
}
