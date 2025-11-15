// AVLTreeDriver.java

public class AVLTreeDriver {
    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        // values ​​that cause different rotations
        tree.insert(10);
        tree.insert(20);
        tree.insert(30); // RR
        tree.insert(5);
        tree.insert(4);  // LL
        tree.insert(8);  // LR
        tree.insert(25); // RL

        System.out.print("Inorder: ");
        tree.inorder();

        System.out.print("Preorder: ");
        tree.preorder();

        System.out.print("Postorder: ");
        tree.postorder();
    }
}