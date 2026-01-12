/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author xaviermckenzie
 */
public class Reminder {


    public String name;
    public String time;    // original user input (AM/PM or 24-hour)
    public int minutes;    // converted to minutes for sorting

    public Reminder(String name, String time) {
        this.name = name;
        this.time = time.trim();
        this.minutes = parseTimeToMinutes(this.time);
    }

    /**
     * Converts time to minutes.
     * Supports:
     *  - "8:30 AM"
     *  - "8:30 PM"
     *  - "14:30"
     *  - "7:05"
     */
    private int parseTimeToMinutes(String t) {
        t = t.trim().toUpperCase();

        boolean isPM = t.endsWith("PM");
        boolean isAM = t.endsWith("AM");

        // Remove AM/PM if present
        if (isAM || isPM) {
            t = t.replace("AM", "").replace("PM", "").trim();
        }

        // Split hours + minutes
        String[] parts = t.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Time must be in HH:MM or HH:MM AM/PM format.");
        }

        int hour = Integer.parseInt(parts[0].trim());
        int minute = Integer.parseInt(parts[1].trim());

        // Convert AM/PM to 24-hour format
        if (isPM && hour != 12) hour += 12;   // 1 PM → 13
        if (isAM && hour == 12) hour = 0;     // 12 AM → 0

        return hour * 60 + minute;
    }

    @Override
    public String toString() {
        return time + "   " + name;
    }
}

