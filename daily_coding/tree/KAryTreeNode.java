package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 7/22/19
 */

public class KAryTreeNode {
    private int val;
    private List<KAryTreeNode> children;

    public KAryTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public int getVal() {
        return this.val;
    }

    public List<KAryTreeNode> getChildren() {
        return this.children;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
