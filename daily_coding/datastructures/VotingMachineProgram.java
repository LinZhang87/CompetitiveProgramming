package datastructures;

import java.util.*;

/**
 * @author lzhang
 * @since 9/22/19
 */

/*
On election day, a voting machine writes data in the form (voter_id, candidate_id) to a text file.
Write a program that reads this file as a stream and returns the top 3 candidates at any given time.
If you find a voter voting more than once, report this as fraud.
 */

public class VotingMachineProgram {
    //Assume we already have the code to stream the input file
    public void topThreeCandidates(int[][] votes) {
        Set<Integer> voters = new HashSet<>();
        Map<Integer, Integer> candidateVotes = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> maxPq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        Map.Entry<Integer, Integer>[] topThree = new Map.Entry[3];

        for(int i = 0; i < votes.length; i++) {
            if(voters.contains(votes[i][0])) {
                System.out.println("Fraud: voter of id " + votes[i][0] + " voted more than once!\n");
            }
            candidateVotes.put(votes[i][1], candidateVotes.getOrDefault(votes[i][1], 0 ) + 1);
            //percolate up


            //retrieve top 3
            int j = 0;
            while(maxPq.size() > 0 && j < 3) {
                topThree[j] = maxPq.poll();
                j++;
            }
            //Add top 3 back to max pq
            for(int k = 0; k < j; k++) {
                maxPq.add(topThree[k]);
            }
        }
    }
}
