package br.com.lwbaleeiro.eng_software.top_150;

import br.com.lwbaleeiro.eng_software.SurfaceAreaVolumeBox;
import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.top_150.MergeSortedArray.merge;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortedArrayTest {

    @Test
    void test() {


        assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, merge(nums1, 3, nums2, 3));
    }
}
