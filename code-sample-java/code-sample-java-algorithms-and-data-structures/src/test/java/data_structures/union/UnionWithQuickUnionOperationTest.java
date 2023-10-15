package data_structures.union;

import codesample.data_structures.union.UnionWithQuickUnionOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnionWithQuickUnionOperationTest {

    private UnionWithQuickUnionOperation<String> union;

    @BeforeEach
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
