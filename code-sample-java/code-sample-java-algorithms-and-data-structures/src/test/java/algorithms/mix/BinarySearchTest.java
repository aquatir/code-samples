package algorithms.mix;

import codesample.algorithms.mix.BinarySearch;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BinarySearchTest {

    @Test
    void test_5_elements() {
        var list = List.of(1, 2, 3, 4, 5);

        assertThat(BinarySearch.search(list, 0)).isEqualTo(-1);
        assertThat(BinarySearch.search(list, 1)).isEqualTo(0);
        assertThat(BinarySearch.search(list, 2)).isEqualTo(1);
        assertThat(BinarySearch.search(list, 3)).isEqualTo(2);
        assertThat(BinarySearch.search(list, 4)).isEqualTo(3);
        assertThat(BinarySearch.search(list, 5)).isEqualTo(4);
        assertThat(BinarySearch.search(list, 6)).isEqualTo(-1);
    }

    @Test
    void test_4_elements() {
        var list = List.of(1, 2, 3, 4);

        assertThat(BinarySearch.search(list, 0)).isEqualTo(-1);
        assertThat(BinarySearch.search(list, 1)).isEqualTo(0);
        assertThat(BinarySearch.search(list, 2)).isEqualTo(1);
        assertThat(BinarySearch.search(list, 3)).isEqualTo(2);
        assertThat(BinarySearch.search(list, 4)).isEqualTo(3);
        assertThat(BinarySearch.search(list, 5)).isEqualTo(-1);
    }
}
