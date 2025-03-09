package br.com.lwbaleeiro.eng_software;

import org.junit.jupiter.api.Test;



public class PalindromeNumberTest {

    @Test
    public void Test1() {

        assert PalindromeNumber.isPalindrome(121);
        assert !PalindromeNumber.isPalindrome(10);
        assert !PalindromeNumber.isPalindrome(-121);
    }

    @Test
    public void Test2() {

        assert PalindromeNumber.isPalindrome2(121);
        assert !PalindromeNumber.isPalindrome2(10);
        assert !PalindromeNumber.isPalindrome2(-121);
    }

    @Test
    public void Test3() {

        assert PalindromeNumber.isPalindrome3(121);
        assert !PalindromeNumber.isPalindrome3(10);
        assert !PalindromeNumber.isPalindrome3(-121);
    }

}
