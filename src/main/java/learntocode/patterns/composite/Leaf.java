package learntocode.patterns.composite;

/**
 * Leaf class extends component but does not implement all of the operations
 */
public class Leaf extends Component {

    @Override
    void operation() {
        System.out.println("Operation of leaf is called");
    }

    @Override
    void addComponents(Component component) throws  UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    void remove(Component component) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    Component getChild(int id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}
