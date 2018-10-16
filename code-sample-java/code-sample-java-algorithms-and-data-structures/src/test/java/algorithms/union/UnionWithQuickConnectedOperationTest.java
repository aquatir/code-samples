package algorithms.union;

import codesample.algorithms.union.UnionWithQuickConnectedOperation;
import org.junit.Assert;
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
        union.connect("aaa", "bbb");
        union.connect("bbb", "ccc");

        Assert.assertTrue(union.areConnected("aaa", "ccc"));
    }

    @Test
    public void createUnlinkedNode_TestNotConnectedToAnything() {

        union.connect("linked_1", "linked_2");
        union.addUnlinkedNode("unlinked_1");

        Assert.assertFalse(union.areConnected("linked_1", "unlinked_1"));
        Assert.assertFalse(union.areConnected("linked_2", "unlinked_1"));

        union.addUnlinkedNode("unlinked_2");

        Assert.assertFalse(union.areConnected("linked_1", "unlinked_2"));
        Assert.assertFalse(union.areConnected("linked_2", "unlinked_2"));
        Assert.assertFalse(union.areConnected("unlinked_1", "unlinked_2"));
    }

    @Test
    public void removeUnionNode_TestNotConnectedAnyMore() {
        union.connect("linked_1", "to_be_unlinked");
        Assert.assertTrue(union.areConnected("linked_1", "to_be_unlinked"));

        union.removeNode("to_be_unlinked");
        Assert.assertFalse(union.areConnected("linked_1", "to_be_unlinked"));
    }
}
