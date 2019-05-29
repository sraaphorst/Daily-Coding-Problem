// StackQueue.java
//
// By Sebastian Raaphorst, 2019.

package dcp.day053;

import java.util.Stack;

public final class StackQueue<T> {
    private Stack<T> lifo;
    private Stack<T> fifo;

    public StackQueue() {
        lifo = new Stack<>();
        fifo = new Stack<>();
    }

    public synchronized void enqueue(final T item) {
        lifo.push(item);
    }

    public synchronized T dequeue() {
        if (fifo.empty())
            while (!lifo.empty())
                fifo.push(lifo.pop());

        return fifo.pop();
    }

    public int size() {
        return lifo.size() + fifo.size();
    }

    public boolean empty() {
        return lifo.empty() && fifo.empty();
    }
}
