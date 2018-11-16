package codesample.userful.booleanresolver.operation;

import codesample.userful.booleanresolver.Expr;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class TwoExprOperation implements Expr {
    private final Expr left;
    private final Expr right;

    @Override
    public Boolean eval() {
        return evaluation(left, right);
    }

    abstract Boolean evaluation(Expr left, Expr right);
}
