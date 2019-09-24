package dp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lzhang
 * @since 6/2/19
 */

public class EditDistance {
    private String fromWord, toWord;
    private int[][] editCost;

    public EditDistance(String word1, String word2) {
        fromWord = word1;
        toWord = word2;
        int n1 = word1.length(), n2 = word2.length();
        editCost = new int[n1 + 1][n2 + 1];
        for(int i = 0; i <= n1; i++) {
            editCost[i][0] = i;
        }
        for(int j = 0; j <= n2; j++) {
            editCost[0][j] = j;
        }
        for(int i = 1; i <= n1; i++) {
            for(int j = 1; j <= n2; j++) {
                int diff = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                editCost[i][j] = editCost[i - 1][j - 1] + diff;
                editCost[i][j] = Math.min(editCost[i][j], Math.min(editCost[i - 1][j], editCost[i][j - 1]) + 1);
            }
        }
    }
    public int minEditDistance() {
        return editCost[fromWord.length()][toWord.length()];
    }
    public Character[][] reconstructMinEditCostAlignment() {
        ArrayDeque<Character> seq1 = new ArrayDeque<>();
        ArrayDeque<Character> seq2 = new ArrayDeque<>();

        int i = fromWord.length(), j = toWord.length();
        while(i > 0 || j > 0) {
            int mis_match = i > 0 && j > 0 ? editCost[i][j] : -1;
            int deletion = i > 0 ? editCost[i - 1][j] : -1;
            int insertion = j > 0 ? editCost[i][j - 1] : -1;
            String step = getPreviousMin(mis_match, deletion, insertion);

            switch(step) {
                case "Substitute":
                    seq1.addFirst(fromWord.charAt(i - 1));
                    seq2.addFirst(toWord.charAt(j - 1));
                    i--;
                    j--;
                    break;
                case "Deletion":
                    seq1.addFirst('-');
                    seq2.addFirst('*');
                    i--;
                    break;
                case "Insertion":
                    seq1.addFirst('+');
                    seq2.addFirst(toWord.charAt(j - 1));
                    j--;
                    break;
            }
        }
        Character[][] alignment = {seq1.toArray(new Character[0]), seq2.toArray(new Character[0])};
        return alignment;
    }

    private String getPreviousMin(int mis_match, int deletion, int insertion) {
        if(deletion < 0) {
            return "Insertion";
        }
        else if(insertion < 0) {
            return "Deletion";
        }
        else if(mis_match <= deletion && mis_match <= insertion) {
            return "Substitute";
        }
        else if(deletion <= mis_match && deletion <= insertion) {
            return "Deletion";
        }
        return "Insertion";
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance("editing", "distance");
        System.out.println(editDistance.minEditDistance());
        Character[][] alignment = editDistance.reconstructMinEditCostAlignment();
        System.out.println(Arrays.toString(alignment[0]));
        System.out.println(Arrays.toString(alignment[1]));
    }
}
