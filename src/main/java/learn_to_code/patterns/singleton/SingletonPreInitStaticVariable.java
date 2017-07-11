package learn_to_code.patterns.singleton;

/**
 * This type of singleton insures an only existence on SingletonPreInitStaticVariable
 * because the creation is done on static field only once.
 *
 * Take note that this type of singleton doesn't provide serialization capabilities automatically.
 * You can however implement your own readResolve() which would return INSTANCE variable
 */
public class SingletonPreInitStaticVariable {
    public static final SingletonPreInitStaticVariable INSTANCE = new SingletonPreInitStaticVariable();

    private SingletonPreInitStaticVariable() {}
}
