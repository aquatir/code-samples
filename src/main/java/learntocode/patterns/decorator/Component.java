package learntocode.patterns.decorator;

/**
 * Components are getting stored one inside another each implementing this method.
 * While getting invoked, each component can do some useful work and then pass control
 * to underlying components.
 */
public interface Component {
    public void componentFunction();
}
