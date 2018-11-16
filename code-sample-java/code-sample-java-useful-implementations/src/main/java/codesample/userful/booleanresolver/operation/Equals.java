package codesample.userful.booleanresolver.operation;

import codesample.userful.booleanresolver.Expr;

public class Equals extends TwoExprOperation {

    public Equals(Expr left, Expr right) {
        super(left, right);
    }

    public static Equals of(Expr left, Expr right) {
        return new Equals(left, right);
    }

    @Override
    public Boolean evaluation(Expr left, Expr right) {
        return left.eval() == right.eval();
    }
}
