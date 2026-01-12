/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author xaviermckenzie
 */
public class HistoryList {

    private class Node {
        Reminder data;
        Node next;

        Node(Reminder r) {
            this.data = r;
        }
    }

    private Node head;

    // Add medication to the history list (front insert)
    public void add(Reminder r) {
        Node n = new Node(r);
        n.next = head;
        head = n;
    }

    // Print history
    public void print() {
        System.out.println("\n=== Medication History ===");

        if (head == null) {
            System.out.println("No medications recorded yet.\n");
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println();
    }
}

