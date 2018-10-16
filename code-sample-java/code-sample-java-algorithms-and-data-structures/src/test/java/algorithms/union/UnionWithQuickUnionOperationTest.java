package algorithms.union;

import codesample.algorithms.union.UnionWithQuickUnionOperation;
import org.junit.Before;
import org.junit.Test;

public class UnionWithQuickUnionOperationTest {

    private UnionWithQuickUnionOperation<String> union;

    @Before
    public void unitUnion() {
        union = new UnionWithQuickUnionOperation<>();
    }

    @Test
    public void unionThreeNodes_CheckTransitiveAreConnectedTrue() {
        GenericUnionTests.unionThreeNodes_CheckTransitiveAreConnectedTrue(union);
    }

    @Test
    public void createUnlinkedNode_TestNotConnectedToAnything() {
        GenericUnionTests.createUnlinkedNode_TestNotConnectedToAnything(union);
    }

    @Test
    public void removeUnionNode_TestNotConnectedAnyMore() {
        GenericUnionTests.removeUnionNode_TestNotConnectedAnyMore(union);
    }
}
