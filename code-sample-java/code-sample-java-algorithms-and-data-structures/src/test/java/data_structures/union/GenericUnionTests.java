package data_structures.union;

import codesample.data_structures.unions.Union;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericUnionTests {

    /* Check connect and union operation works. Also check that areConnected has reflectivity property */
    public static void unionThreeNodes_CheckTransitiveAreConnectedTrue(Union<String> union) {
        union.connect("aaa", "bbb");
        union.connect("bbb", "ccc");
        union.connect("ccc", "ddd");
        union.connect("fff", "ggg");
        union.connect("ggg", "hhh");
        union.connect("aaa", "hhh");

        assertThat(union.areConnected("aaa", "bbb")).isTrue();
        assertThat(union.areConnected("aaa", "ccc")).isTrue();
        assertThat(union.areConnected("aaa", "ddd")).isTrue();
        assertThat(union.areConnected("aaa", "fff")).isTrue();
        assertThat(union.areConnected("aaa", "ggg")).isTrue();

        assertThat(union.areConnected("ddd", "aaa")).isTrue();
        assertThat(union.areConnected("ccc", "aaa")).isTrue();
        assertThat(union.areConnected("bbb", "aaa")).isTrue();
        assertThat(union.areConnected("fff", "aaa")).isTrue();
        assertThat(union.areConnected("ggg", "aaa")).isTrue();

        assertThat(union.areConnected("hhh", "ggg")).isTrue();
        assertThat(union.areConnected("ggg", "hhh")).isTrue();
    }

    public static void createUnlinkedNode_TestNotConnectedToAnything(Union<String> union) {

        union.connect("linked_1", "linked_2");
        union.addUnlinkedNode("unlinked_1");

        assertThat(union.areConnected("linked_1", "unlinked_1")).isFalse();
        assertThat(union.areConnected("linked_2", "unlinked_1")).isFalse();

        union.addUnlinkedNode("unlinked_2");

        assertThat(union.areConnected("linked_1", "unlinked_2")).isFalse();
        assertThat(union.areConnected("linked_2", "unlinked_2")).isFalse();
        assertThat(union.areConnected("unlinked_1", "unlinked_2")).isFalse();
    }

    public static void removeUnionNode_TestNotConnectedAnyMore(Union<String> union) {
        union.connect("linked_1", "to_be_unlinked");
        assertThat(union.areConnected("linked_1", "to_be_unlinked")).isTrue();

        union.removeNode("to_be_unlinked");
        assertThat(union.areConnected("linked_1", "to_be_unlinked")).isFalse();

        union.connect("linked_1", "linked_2");
        union.connect("linked_2", "linked_3");

        union.removeNode("linked_2");

        assertThat(union.areConnected("linked_1", "linked_3")).isTrue();
        assertThat(union.areConnected("linked_1", "linked_2")).isFalse();
        assertThat(union.areConnected("linked_3", "linked_2")).isFalse();

    }
}
