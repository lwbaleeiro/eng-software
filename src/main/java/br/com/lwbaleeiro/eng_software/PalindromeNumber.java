package br.com.lwbaleeiro.eng_software;

/***
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * Example 1:
 *  Input: x = 121
 *  Output: true
 *  Explanation: 121 reads as 121 from left to right and from right to left.
 *
 * Example 2:
 *  Input: x = -121
 *  Output: false
 *  Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 *  Input: x = 10
 *  Output: false
 *  Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 ***/

public class PalindromeNumber {

    // my solution
    public static boolean isPalindrome(int x) {
        String backwards = "";
        String number = String.valueOf(x);

        for (int i = number.length(); i != 0; i--) {
            backwards += Character.toString(number.charAt(i -1));
        }

        if (number.equals(backwards)){
            return true;
        } else {
            return false;
        }
    }

    // Math solution ("Best")
    public static boolean isPalindrome2(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev*10 + x%10;
            x = x/10;
        }
        return (x==rev || x==rev/10);
    }

    public static boolean isPalindrome3(int x) {
        String number = String.valueOf(x);
        String reversed = new StringBuilder(number).reverse().toString();
        return number.equals(reversed);
    }


}
