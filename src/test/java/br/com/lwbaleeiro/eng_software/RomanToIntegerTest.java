package br.com.lwbaleeiro.eng_software;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.RomanToInteger.romanToInt;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RomanToIntegerTest {

    @Test
    public void Test1() {

        assert 3 == romanToInt("III");
    }

    @Test
    public void Test2() {

        assert 58 == romanToInt("LVIII");
    }

    @Test
    public void Test3() {

        assert 1994 == romanToInt("MCMXCIV");
    }

    @Test
    public void Test4() {

        assert 4 == romanToInt("IV");
    }
}
