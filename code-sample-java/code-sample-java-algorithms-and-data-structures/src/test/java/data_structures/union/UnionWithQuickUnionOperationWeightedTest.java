package data_structures.union;

import codesample.data_structures.unions.UnionWithQuickUnionOperationWeighted;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnionWithQuickUnionOperationWeightedTest {

    private UnionWithQuickUnionOperationWeighted<String> union;

    @BeforeEach
    public void unitUnion() {
        union = new UnionWithQuickUnionOperationWeighted<>();
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
