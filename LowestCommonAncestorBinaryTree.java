
import java.util.*;

public class LowestCommonAncestorBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static Scanner sc;

    // Build the binary tree from the given format
    public static TreeNode buildTree() {
        if (!sc.hasNext())
            return null;

        int data = sc.nextInt();
        TreeNode node = new TreeNode(data);

        if (sc.nextBoolean()) {
            node.left = buildTree();
        }

        if (sc.nextBoolean()) {
            node.right = buildTree();
        }

        return node;
    }

    // Find LCA of two given nodes
    public static TreeNode findLCA(TreeNode root, int n1, int n2) {
        if (root == null)
            return null;

        if (root.val == n1 || root.val == n2)
            return root;

        TreeNode leftLCA = findLCA(root.left, n1, n2);
        TreeNode rightLCA = findLCA(root.right, n1, n2);

        if (leftLCA != null && rightLCA != null)
            return root;

        return leftLCA != null ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        // Build the tree
        TreeNode root = buildTree();

        // Read two target nodes
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        TreeNode lca = findLCA(root, n1, n2);
        if (lca != null) {
            System.out.println(lca.val);
        } else {
            System.out.println("LCA not found");
        }

        sc.close();
    }
}
