import java.util.*;

public class IsBalancedBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static Scanner sc;

    // Build tree using the special pre-order format
    public static TreeNode buildTree() {
        if (!sc.hasNext())
            return null;

        int data = sc.nextInt();
        TreeNode node = new TreeNode(data);

        boolean hasLeft = sc.nextBoolean();
        if (hasLeft) {
            node.left = buildTree();
        }

        boolean hasRight = sc.nextBoolean();
        if (hasRight) {
            node.right = buildTree();
        }

        return node;
    }

    // Helper class to hold height and balance status
    static class BalanceStatus {
        boolean isBalanced;
        int height;

        BalanceStatus(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    // Check if tree is balanced and return height
    public static BalanceStatus checkBalanced(TreeNode node) {
        if (node == null)
            return new BalanceStatus(true, 0);

        BalanceStatus left = checkBalanced(node.left);
        BalanceStatus right = checkBalanced(node.right);

        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
        int height = 1 + Math.max(left.height, right.height);

        return new BalanceStatus(isBalanced, height);
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        TreeNode root = buildTree();
        BalanceStatus result = checkBalanced(root);
        System.out.println(result.isBalanced);

        sc.close();
    }
}
