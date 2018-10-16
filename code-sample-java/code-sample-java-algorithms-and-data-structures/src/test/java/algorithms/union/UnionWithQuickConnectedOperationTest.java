package algorithms.union;

import codesample.algorithms.union.UnionWithQuickConnectedOperation;
import org.junit.Before;
import org.junit.Test;

public class UnionWithQuickConnectedOperationTest {

    private UnionWithQuickConnectedOperation<String> union;

    @Before
    public void unitUnion() {
        union = new UnionWithQuickConnectedOperation<>();
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
