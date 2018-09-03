package patterns.observer;

/**
 * Observer can update it's state when called by {@link Subject}
 */
interface Observer {
    void update(int value);
}
