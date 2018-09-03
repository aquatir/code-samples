package patterns.visitor;

/**
 * Visitor interface should describe visit(? extends Client) methods for all subclasses of {@link Client}
 * When concrete client is getting visited, the methods invoked by visitor are dependant on a subclass of Client
 * which was passed in.
 *
 * Different Visitor implementations can have radically different behavior. It's only important that
 * this concrete visitor can work with any type of Client subclasses.
 *
 * If you don't want every single visitor to be able to work with every single client subclass you can
 * divide visitor interface into many interfaces one for each client subclass and only implement those what you need
 * for each concrete Client subclass. This method not only reduce number of visit(? extends Client) methods you should
 * implement but also provide compile-time class-type safety.
 */
interface Visitor {
    void visit(ConcreteClientOne clientOne);
    void visit(ConcreteClientTwo clientTwo);
}
