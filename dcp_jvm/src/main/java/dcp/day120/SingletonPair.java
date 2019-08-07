// SingletonPair.java
//
// By Sebastian Raaphorst, 2019.

package dcp.day120;

enum SingletonPair {
    SINGLETON0(0),
    SINGLETON1(1);

    private final int id;

    SingletonPair(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    private static int counter = 0;
    public synchronized static SingletonPair getInstance() {
        if (counter == 0) {
            counter = 1;
            return SINGLETON0;
        }
        else {
            counter = 0;
            return SINGLETON1;
        }
    }
}
