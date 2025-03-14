package br.com.lwbaleeiro.eng_software;

import org.junit.jupiter.api.Test;

import static br.com.lwbaleeiro.eng_software.ValidParentheses.isValidParentheses;
import static br.com.lwbaleeiro.eng_software.ValidParentheses.isValidParentheses2;

public class ValidParenthesesTest {

    @Test
    public void Test1() {

        //assert isValidParentheses("()");
        assert isValidParentheses2("()[]{}");
        assert !isValidParentheses2("(]");
        assert !isValidParentheses2("({{{{}}}))");
        //assert isValidParentheses("([])");
    }
}
