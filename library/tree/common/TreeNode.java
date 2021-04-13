package tree.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhang
 * @since 3/5/20
 */

public class TreeNode {
    public int val;
    public List<TreeNode> children;

    public TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public void addChildNode(TreeNode child) {
        children.add(child);
    }
}
