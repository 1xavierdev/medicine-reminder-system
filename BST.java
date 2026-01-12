/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author xaviermckenzie
 */
import java.util.List;

class BSTNode {
    Reminder data;
    BSTNode left, right;

    BSTNode(Reminder r) {
        this.data = r;
    }
}

public class BST {

    BSTNode root;

    // Insert a reminder into the BST
    public void insert(Reminder r) {
        root = insertRec(root, r);
    }

    private BSTNode insertRec(BSTNode node, Reminder r) {
        if (node == null) {
            return new BSTNode(r);
        }

        if (r.minutes < node.data.minutes) {
            node.left = insertRec(node.left, r);
        } else {
            node.right = insertRec(node.right, r);
        }

        return node;
    }

    // In-order traversal (sorted by time)
    public void inOrder(List<Reminder> list) {
        inOrderRec(root, list);
    }

    private void inOrderRec(BSTNode node, List<Reminder> list) {
        if (node == null) return;

        inOrderRec(node.left, list);
        list.add(node.data);
        inOrderRec(node.right, list);
    }
}

