/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author xaviermckenzie
 */
import java.util.LinkedList;

public class ReminderQueue {

    private LinkedList<Reminder> list = new LinkedList<>();

    // Add reminder to queue (FIFO)
    public void enqueue(Reminder r) {
        list.addLast(r);
    }

    // Remove and return the next reminder
    public Reminder dequeue() {
        if (list.isEmpty()) return null;
        return list.removeFirst();
    }

    // View next reminder without removing
    public Reminder peek() {
        return list.isEmpty() ? null : list.getFirst();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Print schedule
    public void print() {
        for (Reminder r : list) {
            System.out.println(r);
        }
    }
    
}
