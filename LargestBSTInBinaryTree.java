
import java.util.*;

public class LargestBSTInBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static int preIndex = 0;

    // Construct the tree using preorder and inorder traversals
    public static TreeNode buildTree(int[] preorder, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = inMap.get(rootVal);

        root.left = buildTree(preorder, inorder, inStart, inIndex - 1, inMap);
        root.right = buildTree(preorder, inorder, inIndex + 1, inEnd, inMap);

        return root;
    }

    // Helper class to store info about subtree
    static class BSTInfo {
        boolean isBST;
        int min, max, size, maxBSTSize;

        BSTInfo(boolean isBST, int min, int max, int size, int maxBSTSize) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.size = size;
            this.maxBSTSize = maxBSTSize;
        }
    }

    // Compute the size of the largest BST in a binary tree
    public static BSTInfo largestBST(TreeNode root) {
        if (root == null) {
            return new BSTInfo(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0);
        }

        BSTInfo left = largestBST(root.left);
        BSTInfo right = largestBST(root.right);

        boolean isBST = left.isBST && right.isBST &&
                        (root.val > left.max && root.val < right.min);

        int size = left.size + right.size + 1;

        if (isBST) {
            return new BSTInfo(true,
                    Math.min(root.val, left.min),
                    Math.max(root.val, right.max),
                    size,
                    size);
        } else {
            return new BSTInfo(false, 0, 0, size, Math.max(left.maxBSTSize, right.maxBSTSize));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] preorder = new int[n];
        int[] inorder = new int[n];

        for (int i = 0; i < n; i++) {
            preorder[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            inorder[i] = sc.nextInt();
        }

        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(preorder, inorder, 0, n - 1, inMap);
        int result = largestBST(root).maxBSTSize;

        System.out.println(result);
        sc.close();
    }
}
