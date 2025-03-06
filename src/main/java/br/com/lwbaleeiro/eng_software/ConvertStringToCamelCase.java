package br.com.lwbaleeiro.eng_software;

import java.util.Arrays;

public class ConvertStringToCamelCase {

    // my solution
    static String toCamelCase(String s) {
        String novaFrase = "";
        String letraAnterior = "";

        for (int i = 0; i < s.length(); i++) {

            char aux = s.charAt(i);
            String letra = String.valueOf(aux);

            if (letraAnterior.equals("_") || letraAnterior.equals("-")) {
                novaFrase += letra.toUpperCase();
            } else {
                novaFrase += letra;
            }

            letraAnterior = letra;
        }

        return novaFrase.replace("-", "").replace("_", "");
    }

    // Best practice
    static String toCamelCase2(String str){
        String[] words = str.split("[-_]");
        return Arrays.stream(words, 1, words.length)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .reduce(words[0], String::concat);
    }
}
