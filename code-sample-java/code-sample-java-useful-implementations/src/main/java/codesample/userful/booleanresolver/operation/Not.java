package codesample.userful.booleanresolver.operation;

import codesample.userful.booleanresolver.Expr;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class Not implements Expr {
    private final Expr left;

    @Override
    public Boolean eval() {
        return !left.eval();
    }
}
