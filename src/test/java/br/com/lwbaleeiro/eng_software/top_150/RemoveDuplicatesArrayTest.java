package br.com.lwbaleeiro.eng_software.top_150;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.top_150.RemoveDuplicatesArray.removeDuplicates;
import static br.com.lwbaleeiro.eng_software.top_150.RemoveDuplicatesArray.removeDuplicates2;

public class RemoveDuplicatesArrayTest {

    @Test
    void test() {
        int[] nums = {1, 1, 2};
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        assert(removeDuplicates(nums) == 2);
        assert(removeDuplicates2(nums2) == 5);
    }
}
