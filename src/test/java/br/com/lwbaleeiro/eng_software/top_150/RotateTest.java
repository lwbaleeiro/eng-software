package br.com.lwbaleeiro.eng_software.top_150;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.top_150.Rotate.rotate1;
import static br.com.lwbaleeiro.eng_software.top_150.Rotate.rotate2;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateTest {

//    @Test
//    void test() {
//        int[] nums = {1,2,3,4,5,6,7};
//        int k = 3;
//
//        int[] nums2 = {-1,-100,3,99};
//        int k2 = 2;
//
//
//        assertArrayEquals(new int[]{5,6,7,1,2,3,4}, rotate(nums, k));
//        assertArrayEquals(new int[]{3,99,-1,-100}, rotate(nums2, k2));
//    }

    @Test
    void test1() {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;

        int[] nums2 = {-1,-100,3,99};
        int k2 = 2;


        assertArrayEquals(new int[]{5,6,7,1,2,3,4}, rotate1(nums, k));
        assertArrayEquals(new int[]{3,99,-1,-100}, rotate1(nums2, k2));
    }

    @Test
    void test2() {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;

        int[] nums2 = {-1,-100,3,99};
        int k2 = 2;


        assertArrayEquals(new int[]{5,6,7,1,2,3,4}, rotate2(nums, k));
        assertArrayEquals(new int[]{3,99,-1,-100}, rotate2(nums2, k2));
    }


}
