package algorithms.union;

import codesample.algorithms.union.UnionWithQuickUnionOperationWeightedWithFlattering;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnionWithQuickUnionOperationWeightedWithFlatteringTest {

    private UnionWithQuickUnionOperationWeightedWithFlattering<String> union;

    @BeforeEach
    public void unitUnion() {
        union = new UnionWithQuickUnionOperationWeightedWithFlattering<>();
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
