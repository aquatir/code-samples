package learntocode.javaapi.annotations;

import java.lang.reflect.Method;

public class GettingRuntimeAnnotations {

    public static void main(String args[]) throws NoSuchMethodException {

        Class<?> cl = AnnotatedTestClass.class;

        Method method1 = cl.getMethod("demonstrateAnnotation");
        Method method2 = cl.getMethod("demonstrateValueAnnotationsFiledName");

        TestAnnotation annotation2 = method2.getAnnotation(TestAnnotation.class);
        System.out.println(annotation2.str());
        System.out.println(annotation2.value());

        TestAnnotation annotation1 = method1.getAnnotation(TestAnnotation.class);
        System.out.println(annotation1.str());
        System.out.println(annotation1.value());
    }
}
