package data_structures.union;

import codesample.data_structures.unions.UnionWithQuickUnionOperationWeightedWithFlattering;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnionWithQuickUnionOperationWeightedWithFlatteringTest {

    private UnionWithQuickUnionOperationWeightedWithFlattering<String> union;

    @BeforeEach
    public void unitUnion() {
        union = new UnionWithQuickUnionOperationWeightedWithFlattering<>();
    }

    @Test
    void unionThreeNodes_CheckTransitiveAreConnectedTrue() {
        GenericUnionTests.unionThreeNodes_CheckTransitiveAreConnectedTrue(union);
    }

    @Test
    void createUnlinkedNode_TestNotConnectedToAnything() {
        GenericUnionTests.createUnlinkedNode_TestNotConnectedToAnything(union);
    }

    @Test
    void removeUnionNode_TestNotConnectedAnyMore() {
        GenericUnionTests.removeUnionNode_TestNotConnectedAnyMore(union);
    }
}
