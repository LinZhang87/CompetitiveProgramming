package heap; /**
 * @author lzhang
 * @since 4/1/19
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class MinHeapElement {
    int sum;
    List<Integer> idx;
}
public class SumFromKLists {
    public static List<Integer> sumFromKLists(List<List<Integer>> lists, int N) {
        List<Integer> ans = new ArrayList<>();
        if(N == 0) {
            return ans;
        }
        PriorityQueue<MinHeapElement> minPq = new PriorityQueue<>(new Comparator<MinHeapElement>() {
            @Override
            public int compare(MinHeapElement e1, MinHeapElement e2) {
                return e1.sum - e2.sum;
            }
        });

        MinHeapElement smallest = new MinHeapElement();
        smallest.idx = new ArrayList<>();
        for(int i = 0; i < lists.size(); i++) {
            smallest.sum += lists.get(i).get(0);
            smallest.idx.add(0);
        }
        minPq.add(smallest);
        ans.add(smallest.sum);

        while(ans.size() < N) {
            MinHeapElement curr = minPq.poll();
            if(ans.get(ans.size() - 1) != curr.sum) {
                ans.add(curr.sum);
            }
            List<MinHeapElement> candidates = nextPossibleCandidates(lists, curr);
            if(candidates.size() == 0) {
                break;
            }
            minPq.addAll(candidates);
        }
        return ans;
    }

    private static List<MinHeapElement> nextPossibleCandidates(List<List<Integer>> lists, MinHeapElement minHeapElement) {
        List<MinHeapElement> candidates = new ArrayList<>();

        for(int i = 0; i < lists.size(); i++) {
            List<Integer> currList = lists.get(i);
            int newIdx = minHeapElement.idx.get(i) + 1;
            while(newIdx < currList.size() && currList.get(newIdx) == currList.get(newIdx - 1)) {
                newIdx++;
            }
            if(newIdx < currList.size()) {
                MinHeapElement nextElement = new MinHeapElement();
                nextElement.sum = minHeapElement.sum + currList.get(newIdx) - currList.get(minHeapElement.idx.get(i));
                nextElement.idx = new ArrayList<>(minHeapElement.idx);
                nextElement.idx.set(i, newIdx);
                candidates.add(nextElement);
            }
        }
        return candidates;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(2); list1.add(4); list1.add(7); list1.add(8);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1); list2.add(3); list2.add(5); list2.add(8);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1); lists.add(list2);

        sumFromKLists(lists, 11);
    }
}
