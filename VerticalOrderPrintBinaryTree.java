import java.util.*;

class VerticalOrderPrintBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Pair {
        TreeNode node;
        int hd; 

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static TreeNode buildTree(List<Integer> levelOrder) {
        if (levelOrder == null || levelOrder.size() == 0 || levelOrder.get(0) == -1)
            return null;

        TreeNode root = new TreeNode(levelOrder.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int index = 1;
        while (!queue.isEmpty() && index < levelOrder.size()) {
            TreeNode current = queue.poll();

            // Left child
            if (index < levelOrder.size() && levelOrder.get(index) != -1) {
                current.left = new TreeNode(levelOrder.get(index));
                queue.add(current.left);
            }
            index++;

            // Right child
            if (index < levelOrder.size() && levelOrder.get(index) != -1) {
                current.right = new TreeNode(levelOrder.get(index));
                queue.add(current.right);
            }
            index++;
        }

        return root;
    }

    public static List<Integer> verticalOrderTraversal(TreeNode root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode curr = p.node;
            int hd = p.hd;

            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(curr.val);

            if (curr.left != null)
                queue.add(new Pair(curr.left, hd - 1));
            if (curr.right != null)
                queue.add(new Pair(curr.right, hd + 1));
        }

        List<Integer> result = new ArrayList<>();
        for (List<Integer> col : map.values()) {
            result.addAll(col);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        int totalNodes = (int) Math.pow(2, n) - 1;

        List<Integer> levelOrder = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) {
            levelOrder.add(sc.nextInt());
        }

        TreeNode root = buildTree(levelOrder);
        List<Integer> result = verticalOrderTraversal(root);
        for (int num : result) {
            System.out.print(num + " ");
        }
        sc.close();
    }
}
