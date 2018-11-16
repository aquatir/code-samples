package codesample.userful.booleanresolver;

import codesample.userful.booleanresolver.operation.And;
import codesample.userful.booleanresolver.operation.Equals;
import codesample.userful.booleanresolver.operation.Not;
import codesample.userful.booleanresolver.operation.Or;
import org.junit.Assert;
import org.junit.Test;

public class BooleanExpressionResolverTest {

    @Test
    public void testAnd() {
        Assert.assertTrue(BooleanExpressionResolver.resolve(And.of(BooleanValue.of(true), BooleanValue.of(true))));
        Assert.assertFalse(BooleanExpressionResolver.resolve(And.of(BooleanValue.of(true), BooleanValue.of(false))));
        Assert.assertFalse(BooleanExpressionResolver.resolve(And.of(BooleanValue.of(false), BooleanValue.of(true))));
        Assert.assertFalse(BooleanExpressionResolver.resolve(And.of(BooleanValue.of(false), BooleanValue.of(false))));
    }

    @Test
    public void testOr() {
        Assert.assertTrue(BooleanExpressionResolver.resolve(Or.of(BooleanValue.of(true), BooleanValue.of(true))));
        Assert.assertTrue(BooleanExpressionResolver.resolve(Or.of(BooleanValue.of(true), BooleanValue.of(false))));
        Assert.assertTrue(BooleanExpressionResolver.resolve(Or.of(BooleanValue.of(false), BooleanValue.of(true))));
        Assert.assertFalse(BooleanExpressionResolver.resolve(Or.of(BooleanValue.of(false), BooleanValue.of(false))));
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(BooleanExpressionResolver.resolve(Equals.of(BooleanValue.of(true), BooleanValue.of(true))));
        Assert.assertFalse(BooleanExpressionResolver.resolve(Equals.of(BooleanValue.of(true), BooleanValue.of(false))));
        Assert.assertFalse(BooleanExpressionResolver.resolve(Equals.of(BooleanValue.of(false), BooleanValue.of(true))));
        Assert.assertTrue(BooleanExpressionResolver.resolve(Equals.of(BooleanValue.of(false), BooleanValue.of(false))));
    }

    @Test
    public void testNot() {
        Assert.assertTrue(BooleanExpressionResolver.resolve(Not.of(BooleanValue.of(false))));
        Assert.assertFalse(BooleanExpressionResolver.resolve(Not.of(BooleanValue.of(true))));
    }
}
