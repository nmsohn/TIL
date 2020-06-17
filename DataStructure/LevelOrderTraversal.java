public static void levelOrderTraversal(TreeNode startNode) {
    Queue<TreeNode> queue=new LinkedList<TreeNode>();
    queue.add(startNode);
    while(!queue.isEmpty())
    {
        TreeNode tempNode=queue.poll();
        System.out.printf("%d ",tempNode.data);
        if(tempNode.left!=null)
            queue.add(tempNode.left);
        if(tempNode.right!=null)
            queue.add(tempNode.right);
    }
}