# Medicine Reminder System

A Java medication scheduling application that demonstrates practical applications of Binary Search Trees, Queues, and Linked Lists for time-based task management.

## Overview
Built for CS253 Data Structures & Algorithms, this system helps users manage medication schedules by automatically sorting reminder times and tracking medication history using fundamental data structures.

## Features
- Add medications with custom dosage times
- Automatic chronological sorting using Binary Search Tree
- Next-dose reminders via Queue ordering  
- Medication history tracking with Linked List
- Swing GUI interface

## Technologies
- Java
- Swing (GUI Framework)
- Custom data structure implementations

## Data Structures Used

**Binary Search Tree (BST)**
- Stores medication reminders with time as key
- O(log n) insertion time
- In-order traversal provides automatic sorting

**Queue (FIFO)**
- Maintains sorted daily schedule
- O(1) retrieval of next medication
- Built from BST traversal

**Linked List**
- Logs completed medications
- O(1) insertion for history tracking

## How It Works
1. User inputs medication name and scheduled times
2. Each reminder is inserted into the BST
3. In-order BST traversal creates a sorted Queue
4. Queue front displays the next reminder
5. Completed reminders move to LinkedList history

## How to Run
1. Make sure you have Java installed (JDK 8 or higher)
2. Compile the program:
```bash
javac Main.java
```
3. Run the program:
```bash
java Main
```

## Example Usage
**Input:**
- Vitamin D at 07:30
- Ibuprofen at 09:00  
- Antibiotic at 13:00

**Output Queue:**
07:30 (Vitamin D) → 09:00 (Ibuprofen) → 13:00 (Antibiotic)

## What I Learned
- Choosing appropriate data structures based on performance requirements
- Implementing tree traversal algorithms for practical applications
- Building complete applications using fundamental CS concepts
- Collaborative software development and debugging

## Team
- Xavier McKenzie - BST implementation and sorting logic
- Eldridge Adomako - Queue/LinkedList and GUI interface

CS253 Data Structures & Algorithms | Fall 2025
