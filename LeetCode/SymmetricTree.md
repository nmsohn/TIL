Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric
But the following [1,2,2,null,3,null,3] is not

## Solution
```
public class Solution {
    public bool IsSymmetric(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> st = new Queue<TreeNode>();
        st.Enqueue(root);
        st.Enqueue(root);
        
        while(st.Count != 0){
            TreeNode left = st.Dequeue();
            TreeNode right = st.Dequeue();
            if(left == null && right == null) continue;
            if(left == null || right == null || left.val != right.val) return false;
            st.Enqueue(left.left);
            st.Enqueue(right.right);
            st.Enqueue(left.right);
            st.Enqueue(right.left);
        }
        return true;
    }
}
```