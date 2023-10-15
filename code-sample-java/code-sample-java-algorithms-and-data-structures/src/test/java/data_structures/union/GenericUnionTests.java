package data_structures.union;

import codesample.data_structures.union.Union;
import org.junit.jupiter.api.Assertions;

public class GenericUnionTests {

    /* Check connect and union operation works. Also check that areConnected has reflectivity property */
    public static void unionThreeNodes_CheckTransitiveAreConnectedTrue(Union<String> union) {
        union.connect("aaa", "bbb");
        union.connect("bbb", "ccc");
        union.connect("ccc", "ddd");
        union.connect("fff", "ggg");
        union.connect("ggg", "hhh");
        union.connect("aaa", "hhh");

        Assertions.assertTrue(union.areConnected("aaa", "bbb"));
        Assertions.assertTrue(union.areConnected("aaa", "ccc"));
        Assertions.assertTrue(union.areConnected("aaa", "ddd"));
        Assertions.assertTrue(union.areConnected("aaa", "fff"));
        Assertions.assertTrue(union.areConnected("aaa", "ggg"));

        Assertions.assertTrue(union.areConnected("ddd", "aaa"));
        Assertions.assertTrue(union.areConnected("ccc", "aaa"));
        Assertions.assertTrue(union.areConnected("bbb", "aaa"));
        Assertions.assertTrue(union.areConnected("fff", "aaa"));
        Assertions.assertTrue(union.areConnected("ggg", "aaa"));

        Assertions.assertTrue(union.areConnected("hhh", "ggg"));
        Assertions.assertTrue(union.areConnected("ggg", "hhh"));
    }

    public static void createUnlinkedNode_TestNotConnectedToAnything(Union<String> union) {

        union.connect("linked_1", "linked_2");
        union.addUnlinkedNode("unlinked_1");

        Assertions.assertFalse(union.areConnected("linked_1", "unlinked_1"));
        Assertions.assertFalse(union.areConnected("linked_2", "unlinked_1"));

        union.addUnlinkedNode("unlinked_2");

        Assertions.assertFalse(union.areConnected("linked_1", "unlinked_2"));
        Assertions.assertFalse(union.areConnected("linked_2", "unlinked_2"));
        Assertions.assertFalse(union.areConnected("unlinked_1", "unlinked_2"));
    }

    public static void removeUnionNode_TestNotConnectedAnyMore(Union<String> union) {
        union.connect("linked_1", "to_be_unlinked");
        Assertions.assertTrue(union.areConnected("linked_1", "to_be_unlinked"));

        union.removeNode("to_be_unlinked");
        Assertions.assertFalse(union.areConnected("linked_1", "to_be_unlinked"));

        union.connect("linked_1", "linked_2");
        union.connect("linked_2", "linked_3");
        union.removeNode("linked_2");
        Assertions.assertTrue(union.areConnected("linked_1", "linked_3"));
        Assertions.assertFalse(union.areConnected("linked_1", "linked_2"));
        Assertions.assertFalse(union.areConnected("linked_3", "linked_2"));

    }
}
