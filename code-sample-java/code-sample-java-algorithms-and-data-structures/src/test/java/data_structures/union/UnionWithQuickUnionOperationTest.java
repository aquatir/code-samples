package data_structures.union;

import codesample.data_structures.unions.UnionWithQuickUnionOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnionWithQuickUnionOperationTest {

    private UnionWithQuickUnionOperation<String> union;

    @BeforeEach
    public void unitUnion() {
        union = new UnionWithQuickUnionOperation<>();
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
