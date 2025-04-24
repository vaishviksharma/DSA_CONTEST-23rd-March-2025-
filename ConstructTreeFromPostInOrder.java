import java.util.*;

public class ConstructTreeFromPostInOrder {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static int postIndex;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return construct(inorder, postorder, 0, inorder.length - 1, inorderMap);
    }

    public static TreeNode construct(int[] inorder, int[] postorder, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = map.get(rootVal);

        // Build right subtree before left subtree (since postorder is L-R-Root)
        root.right = construct(inorder, postorder, inIndex + 1, inEnd, map);
        root.left = construct(inorder, postorder, inStart, inIndex - 1, map);

        return root;
    }

    // Display function as per sample output
    public static void display(TreeNode node) {
        if (node == null) return;

        String left = (node.left != null) ? String.valueOf(node.left.val) : "END";
        String right = (node.right != null) ? String.valueOf(node.right.val) : "END";

        System.out.println(left + " => " + node.val + " <= " + right);

        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read postorder
        int n = sc.nextInt();
        int[] postorder = new int[n];
        for (int i = 0; i < n; i++) {
            postorder[i] = sc.nextInt();
        }

        // Read inorder
        int m = sc.nextInt();
        int[] inorder = new int[m];
        for (int i = 0; i < m; i++) {
            inorder[i] = sc.nextInt();
        }

        TreeNode root = buildTree(inorder, postorder);
        display(root);

        sc.close();
    }
}
