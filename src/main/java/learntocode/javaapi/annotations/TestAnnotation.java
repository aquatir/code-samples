package learntocode.javaapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Annotations are a powerful mechanism, which can simplify work of different code-analise tools, allow to build
 * projects with compile-time-only dependencies, reduce number of configuration files in you source codes and so on
 * </p>
 * <p>
 * Each annotation can have one out of 3 RetentionPolicies
 * <ul>
 * <li>RetentionPolicy.SOURCE. Annotation will be kept only on source code level</li>
 * <li>RetentionPolicy.CLASS. Annotation will be kept in source code AND in compiled jars. It will be
 * removed in Runtime.</li>
 * <li>RetentionPolicy.RUNTIME. Annotation will be kept in runtime, allowing you to analyze it.</li>
 * </ul>
 * </p>
 * <p>
 * Also, annotations can have target (targets) which limits their usage. E.g. ElementType.METHOD mean that this
 * annotation can only be used with methods.
 * </p>
 * <p>
 * Annotations can have field with name {@literal "value"}. This and only this field can be filled with annotations
 * without specifying annotations filed's name. See {@link AnnotatedTestClass} for more info.
 * </p>
 * <p>
 * See {@link GettingRuntimeAnnotations} to get more info about using Runtime annotations
 * </p>
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // RetentionPolicy.CLASS RetentionPolicy.SOURCE
public @interface TestAnnotation {
    String str() default "Hello";
    int value();
}

