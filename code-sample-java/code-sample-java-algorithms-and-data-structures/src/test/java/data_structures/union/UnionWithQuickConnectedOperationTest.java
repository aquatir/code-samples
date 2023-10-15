package data_structures.union;

import codesample.data_structures.unions.UnionWithQuickConnectedOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnionWithQuickConnectedOperationTest {

    private UnionWithQuickConnectedOperation<String> union;

    @BeforeEach
    public void unitUnion() {
        union = new UnionWithQuickConnectedOperation<>();
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
