package patterns.state;

/**
 * State interface defines all possible behaviors available for an object you are trying to work with ({@link Context}).
 * Different state implementations define what exactly are you supposed to do when your object is in concrete state
 */
public interface State {
    void operation();
}
