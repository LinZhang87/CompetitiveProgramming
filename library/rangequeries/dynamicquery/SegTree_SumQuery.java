package rangequeries.dynamicquery;

/**
 * @author lzhang
 * @since 9/11/20
 */

public class SegTree_SumQuery {
    int leftMost, rightMost;    //the range this node is responsible for
    SegTree_SumQuery lChild, rChild;    //left and right child nodes
    int sum;    //node's current sum

    public SegTree_SumQuery(int leftMost, int rightMost, int[] a) {
        this.leftMost = leftMost;
        this.rightMost = rightMost;
        if(leftMost == rightMost) {
            sum = a[leftMost];
        }
        else {
            int mid = leftMost + (rightMost - leftMost) / 2;
            lChild = new SegTree_SumQuery(leftMost, mid, a);
            rChild = new SegTree_SumQuery(mid + 1, rightMost, a);
            recalc();
        }
    }

    public void recalc() {
        if(leftMost == rightMost) return;
        sum = lChild.sum + rChild.sum;
    }

    public void pointUpdate(int index, int newVal) {
        if(leftMost == rightMost) {
            sum = newVal;
            return;
        }
        if(index <= lChild.rightMost) lChild.pointUpdate(index, newVal);
        else rChild.pointUpdate(index, newVal);
        recalc();
    }

    public int rangeSum(int l, int r) {
        if(l > rightMost || r < leftMost) return 0;
        if(l <= leftMost && r >= rightMost) return sum;
        return lChild.rangeSum(l, r) + rChild.rangeSum(l, r);
    }
}
