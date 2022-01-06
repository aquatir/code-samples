package algorithms.union;

import codesample.algorithms.union.UnionWithQuickUnionOperationWeighted;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnionWithQuickUnionOperationWeightedTest {

    private UnionWithQuickUnionOperationWeighted<String> union;

    @BeforeEach
    public void unitUnion() {
        union = new UnionWithQuickUnionOperationWeighted<>();
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
