package com.knox.leetcode.tree;

import android.util.Log;

public class MyBinaryTree {

    private static final String TAG = MyBinaryTree.class.getName();

    // 二叉树
    public Node root;

    // 前序遍历
    // 递推公式：preOrder(r) = print r->preOrder(r->left)->preOrder(r->right)
    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        Log.d(TAG, "preOrder: " + root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序遍历
    // 递推公式：inOrder(r) = inOrder(r->left)->print r->inOrder(r->right)
    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        Log.d(TAG, "inOrder: " + root.data);
        inOrder(root.right);
    }

    // 后序遍历
    // 递推公式：postOrder(r) = postOrder(r->left)->postOrder(r->right)->print r
    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        Log.d(TAG, "postOrder: " + root.data);
    }

    private static class Node {
        private Node left;
        private Node right;
        private int data;

        public Node(int data) {
            this.data = data;
        }
    }
}
