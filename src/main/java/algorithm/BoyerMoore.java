package algorithm;

import java.util.ArrayList;
import java.util.List;

public class BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pattern the pattern string
     * @param R the alphabet size
     */
    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    /**
     * Returns an array of occurrence indices of the pattern string in the text string.
     *
     * @param  txt the text string
     * @return an array of occurrence indices of the pattern string in the text string.
     *
     */
    public Integer[] search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        List<Integer> matches = new ArrayList<>();
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) {
                matches.add(i); // found
                skip = m;
            }
        }
        return matches.toArray(new Integer[matches.size()]);
    }


    /**
     * Returns an array of occurrence indices of the pattern string in the text string.
     *
     * @param  text the text string
     * @return an array of occurrence indices of the pattern string in the text string.
     *
     */
    public Integer[] search(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        List<Integer> matches = new ArrayList<>();
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) {
                matches.add(i); // found
                skip = m;
            }
        }
        return matches.toArray(new Integer[matches.size()]);                    // not found
    }


    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String pat = "dos perros";
        String txt = "Hola mi nombre es miguel y tengo dos perrosdos perros. Me estan fastidiando con esta prueba de mierda. Mis dos perros son Schnauzzer.";
        char[] pattern = pat.toCharArray();
        char[] text    = txt.toCharArray();

        BoyerMoore boyermoore1 = new BoyerMoore(pat);
        BoyerMoore boyermoore2 = new BoyerMoore(pattern, 256);
        Integer[] offset1 = boyermoore1.search(txt);
//        int offset2 = boyermoore2.search(text);

        System.out.print("Se han encontrado " + offset1.length + " coincidencias.");

//        // print results
//        System.out.println("text:    " + txt);
//
//        System.out.print("pattern: ");
//        System.out.print("offset1: " + offset1);




//        for (int i = 0; i < offset1; i++)
//            System.out.print(" ");
//        System.out.println(pat);
//
//        System.out.print("pattern: ");
//        for (int i = 0; i < offset2; i++)
//            System.out.print(" ");
//        System.out.println(pat);
    }
}
