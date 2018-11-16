package codesample.userful.booleanresolver.operation;

import codesample.userful.booleanresolver.Expr;

public class And extends TwoExprOperation {

    public And(Expr left, Expr right) {
        super(left, right);
    }

    public static And of(Expr left, Expr right) {
        return new And(left, right);
    }

    @Override
    public Boolean evaluation(Expr left, Expr right) {
        if (left.eval())
            return right.eval();
        else
            return false;
    }
}
