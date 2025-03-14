package br.com.lwbaleeiro.eng_software;

import java.util.LinkedList;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 ***/

public class LongestCommonPrefix {

    // My solution (it doesn't work!!!)
    public static String dontWork(String[] strs) {
        LinkedList<String> prefix = new LinkedList<>();
        LinkedList<String> containsPrefix = new LinkedList<>();

        String currentWord = strs[0];
        for (int aux = 0; aux < currentWord.length(); aux++ ) {
            prefix.add(String.valueOf(currentWord.charAt(aux)));
        }

        for (int i = 1; i < strs.length; i++) {
            String thisWord = strs[i];
            for (int aux = 0; aux < thisWord.length(); aux++ ) {
                if (prefix.contains(String.valueOf(thisWord.charAt(aux)))) {
                    containsPrefix.add(String.valueOf(thisWord.charAt(aux)));
                }
            }
        }

        if (!containsPrefix.isEmpty()) {
            String returnThis = "";
            for (String aux2 : containsPrefix) {
                returnThis += aux2;
            }

            return returnThis;
        }

        return "";
    }

    // Best solution - horizontal scan
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        // Pega a primeira palavra do elemento do array;
        String prefix = strs[0];

        // Percorre o restante do array
        for (int i = 1; i < strs.length; i++) while (
                // Para cada elemento restante do array (pulando o primeiro),
                // enquanto as strings da primeira palavra do array não é igual à palavra que ele esta percorrendo no momento...
                strs[i].indexOf(prefix) != 0
        ) {
            // É removido o ultimo character da primeira palavra do array e o programa continua...
            prefix = prefix.substring(0, prefix.length() -1);

            // Ele verifica se essa palavra ainda contém letras... Caso não encerra a função.
            if (prefix.isEmpty()) return "";
        }
        return prefix;
    }

    // Vertical solution
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // Percorre apenas a primeira palavra do array
        for (int i = 0; i < strs[0].length(); i++) {
            // Aqui ele pega um character por vês da primeira palavra do array
            char c = strs[0].charAt(i);

            // Aqui percorre t0d0 o array, pulando o primeiro elemento
            for (int j = 1; j < strs.length; j++) {
                // Caso i (representa a posição da primeira palavra do array) seja igual ao
                // comprimento da palavra que ele esta percorrendo no momento, ou...
                // O character da posição atual, no index da palavra atual for diferente do character da posição do
                // primeiro elemento do array, ele remove esse character do primeiro elemento do array, e continua...
                if (i == strs[j].length() || strs[j].charAt(i) != c) return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

}
