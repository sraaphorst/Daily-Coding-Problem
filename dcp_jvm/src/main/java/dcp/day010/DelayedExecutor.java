package dcp.day010;

import java.util.function.Supplier;

final public class DelayedExecutor {
    private DelayedExecutor() {
        throw new AssertionError();
    }

    public static <T> T execute(final Supplier<T> f, long ms) throws InterruptedException {
        Thread.sleep(ms);
        return f.get();
    }
}
