package codesample.userful.booleanresolver.operation;

import codesample.userful.booleanresolver.Expr;

public class Or extends TwoExprOperation {

    public Or(Expr left, Expr right) {
        super(left, right);
    }

    public static Or of(Expr left, Expr right) {
        return new Or(left, right);
    }

    @Override
    public Boolean evaluation(Expr left, Expr right) {
        if (left.eval())
            return true;
        else
            return right.eval();
    }
}