package codesample.frameworks.aspectJ;

/**
 * An example of aspectJ with .aj class. Note that is has specific aspect syntax. You would need some extra
 * dependencies to weave this class into your application.
 *
 * For aspectJ syntax see official guide:
 *  https://eclipse.org/aspectj/doc/next/progguide/semantics.html#semantics-intro
 *
 * See baeldung site for extra information about weaving aspects into your code.
 *  http://www.baeldung.com/aspectj
 */
public aspect AspectAsNormalAspectClass {

    pointcut anyMethodExecuted(): execution(* *(..)) && within(codesample.frameworks.aspectJ.ExampleRunner);

    after(): anyMethodExecuted() {
        System.out.println("After aspect called from " + this.getClass().getSimpleName() + " jp: " + thisJoinPoint.getSignature().toString());
    }
}
