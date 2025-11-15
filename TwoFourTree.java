import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Node class for 2-4 Tree
class TwoFourNode {
    List<Integer> keys;
    List<TwoFourNode> children;
    TwoFourNode parent;

    public TwoFourNode() {
        keys = new ArrayList<>();
        children = new ArrayList<>();
        parent = null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isFull() {
        return keys.size() == 3;
    }

    public void insertKey(int key) {
        keys.add(key);
        Collections.sort(keys);
    }

    public TwoFourNode getNextChild(int key) {
        int i = 0;
        while (i < keys.size() && key > keys.get(i)) {
            i++;
        }
        return children.get(i);
    }
}

public class TwoFourTree {

    private TwoFourNode root;

    public TwoFourTree() {
        root = new TwoFourNode();
    }

    public void insert(int key) {
        TwoFourNode node = root;

        // Descend to leaf
        while (!node.isLeaf()) {
            node = node.getNextChild(key);
        }

        // Insert key
        node.insertKey(key);

        // Split if needed
        while (node != null && node.isFull()) {
            split(node);
            node = node.parent;
        }
    }

    private void split(TwoFourNode node) {
        // Middle key to promote
        int midIndex = 1;
        int midKey = node.keys.get(midIndex);

        // Left and Right nodes
        TwoFourNode left = new TwoFourNode();
        left.keys.add(node.keys.get(0));
        TwoFourNode right = new TwoFourNode();
        right.keys.add(node.keys.get(2));

        // If node has children, distribute them
        if (!node.isLeaf()) {
            left.children.add(node.children.get(0));
            left.children.add(node.children.get(1));
            right.children.add(node.children.get(2));
            right.children.add(node.children.get(3));

            // Update parent references
            for (TwoFourNode child : left.children) child.parent = left;
            for (TwoFourNode child : right.children) child.parent = right;
        }

        // If node is root
        if (node.parent == null) {
            root = new TwoFourNode();
            root.keys.add(midKey);
            root.children.add(left);
            root.children.add(right);
            left.parent = root;
            right.parent = root;
        } else {
            // Insert middle key to parent
            TwoFourNode parent = node.parent;
            parent.insertKey(midKey);

            // Remove old child and add new children
            parent.children.remove(node);
            int pos = 0;
            while (pos < parent.children.size() && parent.children.get(pos).keys.get(0) < midKey) {
                pos++;
            }
            parent.children.add(pos, left);
            parent.children.add(pos + 1, right);
            left.parent = parent;
            right.parent = parent;
        }
    }

    // Inorder traversal
    public void inorder() {
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();
    }

    private void inorder(TwoFourNode node) {
        if (node == null) return;

        if (node.isLeaf()) {
            for (int key : node.keys) System.out.print(key + " ");
        } else {
            int i;
            for (i = 0; i < node.keys.size(); i++) {
                if (i < node.children.size()) inorder(node.children.get(i));
                System.out.print(node.keys.get(i) + " ");
            }
            if (i < node.children.size()) inorder(node.children.get(i));
        }
    }
}