package br.com.lwbaleeiro.eng_software.top_150;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.top_150.RemoveElement.removeElement;
import static br.com.lwbaleeiro.eng_software.top_150.RemoveElement.removeElement2;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RemoveElementTest {

    @Test
    void test() {
        int[] nums1 = {3, 2, 2, 3};

        assertArrayEquals(new int[]{2, 2}, removeElement(nums1, 3));
    }

    @Test
    void test2() {
        int[] nums1 = {3, 2, 2, 3};
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};

       //assert(removeElement2(nums1, 3) == 2);
        assert(removeElement2(nums2, 2) == 5);
    }
}
