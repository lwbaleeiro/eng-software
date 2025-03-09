package br.com.lwbaleeiro.eng_software;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TwoSumTest {

    @Test
    public void Test1() {
        int[] testArray = new int[] {3, 2, 4};
        int[] response = new int [] {1,2};

        assertArrayEquals(response, TwoSum.twoSum(testArray,6));
        assertArrayEquals(response, TwoSum.twoSum(testArray,6));
        assertArrayEquals(response, TwoSum.twoSum3(testArray,6));
    }
}
