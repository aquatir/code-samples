package patterns.observer;

/**
 * Observer can update it's state when called by {@link Subject}
 */
public interface Observer {
    public void update(int value);
}
