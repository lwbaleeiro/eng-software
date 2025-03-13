package br.com.lwbaleeiro.eng_software;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.LongestCommonPrefix.longestCommonPrefix;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonPrefixTest {

    @Test
    public void test() {
        String[] input = {"flower","flow","flight"};

        assertEquals("fl", longestCommonPrefix(input));
    }

    @Test
    public void test1() {
        String[] input = {"dog","racecar","car"};
        assertEquals("", longestCommonPrefix(input));
    }
}
