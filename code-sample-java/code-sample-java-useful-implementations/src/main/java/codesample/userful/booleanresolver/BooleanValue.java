package codesample.userful.booleanresolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class BooleanValue implements Expr {
    private final Boolean value;

    @Override
    public Boolean eval() {
        return value;
    }
}