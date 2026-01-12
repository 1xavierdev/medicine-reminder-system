/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author xaviermckenzie
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MedicationGUI extends JFrame {

    private BST bst = new BST();
    private ReminderQueue queue = new ReminderQueue();
    private HistoryList history = new HistoryList();

    private List<Reminder> historyDisplay = new ArrayList<>();
    private JTextArea outputArea;

    public MedicationGUI() {

        // === WINDOW SETTINGS ===
        setTitle("Medication Reminder System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 600);
        setLocationRelativeTo(null);

        // MAIN BACKGROUND
        getContentPane().setBackground(new Color(242, 245, 250));  // soft grey-blue

        // MAIN CENTER CARD
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
                new EmptyBorder(20, 30, 20, 30)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        // SHADOW EFFECT
        card.setOpaque(true);

        // TITLE
        JLabel title = new JLabel("MEDICATION REMINDER SYSTEM");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(40, 40, 50));

        JLabel subtitle = new JLabel("Add, schedule, and track your daily medications");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(110, 110, 130));

        card.add(title);
        card.add(subtitle);
        card.add(Box.createRigidArea(new Dimension(0, 25)));

        // BUTTON PANEL (CENTERED)
        JPanel buttonGrid = new JPanel(new GridLayout(2, 3, 15, 15));
        buttonGrid.setOpaque(false);

        JButton addBtn = makeButton("Add Medication");
        JButton scheduleBtn = makeButton("Generate Schedule");
        JButton nextBtn = makeButton("Show Next");
        JButton takeBtn = makeButton("Mark Next as Taken");
        JButton historyBtn = makeButton("View History");
        JButton clearBtn = makeButton("Clear Output");

        buttonGrid.add(addBtn);
        buttonGrid.add(scheduleBtn);
        buttonGrid.add(nextBtn);
        buttonGrid.add(takeBtn);
        buttonGrid.add(historyBtn);
        buttonGrid.add(clearBtn);

        card.add(buttonGrid);
        card.add(Box.createRigidArea(new Dimension(0, 20)));

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(210, 210, 220));
        card.add(sep);
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // OUTPUT LABEL
        JLabel outputLabel = new JLabel("Output");
        outputLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        outputLabel.setForeground(new Color(40, 40, 50));
        outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(outputLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // WIDE OUTPUT BOX — CENTERED
        outputArea = new JTextArea(14, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 210)));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(scrollPane);

        // CENTERING THE CARD IN THE SCREEN
        setLayout(new GridBagLayout());
        add(card);

        // ============ BUTTON ACTIONS ============

        // Add Medication (NOW SUPPORTS AM/PM)
        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Medication name:");
            if (name == null || name.isBlank()) return;

            String time = JOptionPane.showInputDialog(this, "Time (e.g., 8:30 AM or 14:30):");
            if (time == null || time.isBlank()) return;

            try {
                Reminder r = new Reminder(name.trim(), time.trim());
                bst.insert(r);
                appendOutput("Added: " + r);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid time format. Please use HH:MM or HH:MM AM/PM.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Generate Today’s Schedule
        scheduleBtn.addActionListener(e -> {
            List<Reminder> sorted = new ArrayList<>();
            bst.inOrder(sorted);

            queue = new ReminderQueue();
            for (Reminder r : sorted) queue.enqueue(r);

            appendOutput("\n=== Today's Schedule ===");
            if (sorted.isEmpty()) appendOutput("No reminders added yet.");
            else sorted.forEach(r -> appendOutput(r.toString()));
        });

        // Show Next
        nextBtn.addActionListener(e -> {
            Reminder next = queue.peek();
            appendOutput(next == null ? "No reminders left." : "Next reminder: " + next);
        });

        // Mark Next as Taken
        takeBtn.addActionListener(e -> {
            Reminder taken = queue.dequeue();
            if (taken == null) appendOutput("Nothing to mark as taken.");
            else {
                history.add(taken);
                historyDisplay.add(taken);
                appendOutput("Marked as taken: " + taken);
            }
        });

        // View History
        historyBtn.addActionListener(e -> {
            appendOutput("\n=== Taken Medications ===");
            if (historyDisplay.isEmpty()) appendOutput("None taken yet.");
            else historyDisplay.forEach(r -> appendOutput(r.toString()));
        });

        // Clear Output
        clearBtn.addActionListener(e -> outputArea.setText(""));
    }


    // Nice rounded modern buttons
    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(238, 241, 251)); // soft blue
        btn.setFont(new Font("SansSerif", Font.PLAIN, 15));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        return btn;
    }

    private void appendOutput(String line) {
        outputArea.append(line + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MedicationGUI().setVisible(true));
    }
}
