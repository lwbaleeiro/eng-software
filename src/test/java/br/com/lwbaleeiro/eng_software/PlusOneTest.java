package br.com.lwbaleeiro.eng_software;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.PlusOne.plusOne;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PlusOneTest {

    @Test
    public void Test1() {

        assertArrayEquals(new int[]{1, 2, 4}, plusOne(new int[]{1, 2, 3}));

        assertArrayEquals(new int[]{4, 3, 2, 2}, plusOne(new int[]{4, 3, 2, 1}));

        assertArrayEquals(new int[]{1, 0}, plusOne(new int[]{9}));

        assertArrayEquals(new int[]{1, 0, 0}, plusOne(new int[]{9, 9}));
    }
}
