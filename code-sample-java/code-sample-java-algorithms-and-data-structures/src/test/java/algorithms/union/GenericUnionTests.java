package algorithms.union;

import codesample.algorithms.union.Union;
import org.junit.Assert;

public class GenericUnionTests {

    /* Check connect and union operation works. Also check that areConnected has reflection property */
    public static void unionThreeNodes_CheckTransitiveAreConnectedTrue(Union<String> union) {
        union.connect("aaa", "bbb");
        union.connect("bbb", "ccc");
        union.connect("ccc", "ddd");
        union.connect("fff", "ggg");
        union.connect("ggg", "hhh");
        union.connect("aaa", "hhh");

        Assert.assertTrue(union.areConnected("aaa", "bbb"));
        Assert.assertTrue(union.areConnected("aaa", "ccc"));
        Assert.assertTrue(union.areConnected("aaa", "ddd"));
        Assert.assertTrue(union.areConnected("aaa", "fff"));
        Assert.assertTrue(union.areConnected("aaa", "ggg"));

        Assert.assertTrue(union.areConnected("ddd", "aaa"));
        Assert.assertTrue(union.areConnected("ccc", "aaa"));
        Assert.assertTrue(union.areConnected("bbb", "aaa"));
        Assert.assertTrue(union.areConnected("fff", "aaa"));
        Assert.assertTrue(union.areConnected("ggg", "aaa"));
    }

    public static void createUnlinkedNode_TestNotConnectedToAnything(Union<String> union) {

        union.connect("linked_1", "linked_2");
        union.addUnlinkedNode("unlinked_1");

        Assert.assertFalse(union.areConnected("linked_1", "unlinked_1"));
        Assert.assertFalse(union.areConnected("linked_2", "unlinked_1"));

        union.addUnlinkedNode("unlinked_2");

        Assert.assertFalse(union.areConnected("linked_1", "unlinked_2"));
        Assert.assertFalse(union.areConnected("linked_2", "unlinked_2"));
        Assert.assertFalse(union.areConnected("unlinked_1", "unlinked_2"));
    }

    public static void removeUnionNode_TestNotConnectedAnyMore(Union<String> union) {
        union.connect("linked_1", "to_be_unlinked");
        Assert.assertTrue(union.areConnected("linked_1", "to_be_unlinked"));

        union.removeNode("to_be_unlinked");
        Assert.assertFalse(union.areConnected("linked_1", "to_be_unlinked"));

        union.connect("linked_1", "linked_2");
        union.connect("linked_2", "linked_3");
        union.removeNode("linked_2");
        Assert.assertTrue(union.areConnected("linked_1", "linked_3"));
        Assert.assertFalse(union.areConnected("linked_1", "linked_2"));
        Assert.assertFalse(union.areConnected("linked_3", "linked_2"));

    }
}
