package br.com.lwbaleeiro.eng_software.top_150;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.top_150.MajorityElements.majorityElement;
import static br.com.lwbaleeiro.eng_software.top_150.MajorityElements.majorityElementBest;

public class MajorityElementsTest {
//
//    @Test
//    void test1() {
//        int[] nums = {2,2,1,1,1,2,2};
//
//        assert majorityElement(nums) == 2;
//    }

    @Test
    void test2() {
        int[] nums = {2,2,1,1,1,2,2};

        assert majorityElementBest(nums) == 2;
    }
}
