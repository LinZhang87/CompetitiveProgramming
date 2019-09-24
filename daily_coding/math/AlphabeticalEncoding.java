package math;

/**
 * @author lzhang
 * @since 6/26/19
 */

/*
This problem was asked by Dropbox.

Spreadsheets often use this alphabetical encoding for its columns: "A", "B", "C", ..., "AA", "AB", ..., "ZZ", "AAA", "AAB", ....

Given a column number, return its alphabetical column id. For example, given 1, return "A". Given 27, return "AA".
 */

public class AlphabeticalEncoding {
    public static String alphabeticalEncoding(int colNum) {
        StringBuilder sb = new StringBuilder();
        while(colNum > 26) {
            sb.append('A');
            colNum -= 26;
        }
        sb.append((char)('A' + colNum - 1));
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(alphabeticalEncoding(1));
        System.out.println(alphabeticalEncoding(26));
        System.out.println(alphabeticalEncoding(53));
    }
}
