package learn_to_code.frameworks.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * An example of aspectJ with normal .java class with anotations.
 *
 * For aspectJ syntax see official guide:
 *  https://eclipse.org/aspectj/doc/next/progguide/semantics.html#semantics-intro
 *
 * See baeldung site for extra information about weaving aspects into your code.
 *  http://www.baeldung.com/aspectj
 */
@Aspect
public class AspectAsAnnotatedClass {

    @Pointcut("execution(* *.*(..)) && within(learn_to_code.frameworks.aspectJ.ExampleRunner)")
    public void beforeAny() {}

    @Before("beforeAny()")
    public void beforeAnyCalled(JoinPoint jp) {
        System.out.println("Before aspect called from " + this.getClass().getSimpleName() + " jp: " + jp.getSignature().toString());
    }
}
