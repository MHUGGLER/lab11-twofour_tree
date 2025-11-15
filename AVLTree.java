// AVLTree.java

class AVLNode {
    int key;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int k) {
        key = k;
        height = 1; // the begin as a leaf knot
    }
}

public class AVLTree {

    AVLNode root;

    // height of a node (returns 0 if null)
    private int height(AVLNode n) {
        if (n == null) return 0;
        return n.height;
    }

    // greater of two numbers
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // balancing factor
    private int getBalance(AVLNode n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    // simple rotation to the right
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = 1 + max(height(y.left), height(y.right));
        x.height = 1 + max(height(x.left), height(x.right));

        return x;
    }

    // simple left rotation
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = 1 + max(height(x.left), height(x.right));
        y.height = 1 + max(height(y.left), height(y.right));

        return y;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    // insertion with rebalancing
    private AVLNode insertRec(AVLNode node, int key) {

        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        } else {
            // We will not insert duplicates.
            return node;
        }

        // update height
        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // 4 cases of imbalance

        // Caso 1: left-left (LL)
        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }

        // Caso 2: right-right (RR)
        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }

        // Caso 3: left right (LR)
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Caso 4: right-left (RL)
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // CROSSINGS
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(AVLNode n) {
        if (n != null) {
            inorderRec(n.left);
            System.out.print(n.key + " ");
            inorderRec(n.right);
        }
    }

    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(AVLNode n) {
        if (n != null) {
            System.out.print(n.key + " ");
            preorderRec(n.left);
            preorderRec(n.right);
        }
    }

    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(AVLNode n) {
        if (n != null) {
            postorderRec(n.left);
            postorderRec(n.right);
            System.out.print(n.key + " ");
        }
    }
}