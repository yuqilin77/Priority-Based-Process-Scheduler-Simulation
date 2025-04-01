# ⏱️ Priority-Based Process Scheduler Simulation

This Java simulation models a simplified operating system process scheduler using a heap-based priority queue. It simulates processes arriving at different times, executing based on priority, and incorporates an **aging mechanism** to prevent starvation.

## 📌 Features

- Simulates arrival and execution of processes over logical time units
- Uses `HeapAdaptablePriorityQueue` to dynamically manage process execution order
- Automatically updates priorities for long-waiting processes (aging policy)
- Prints execution trace per time step and calculates average wait time
- Reads input from `process_scheduling_input.txt`, writes output to `process_scheduling_output.txt`

## 💡 Project Context

This project was built for **CS526 - Data Structures & Algorithms**, Boston University. It demonstrates skills in simulation modeling, queue-based scheduling logic, file I/O, and priority-based decision-making.

## 🛠️ Technologies Used

- Java (SE)
- Priority Queues / Min Heap
- File I/O (BufferedReader / PrintWriter)
- Simulation Algorithms
- Object-Oriented Design

## 🧪 Sample Input Format

Each line in the input file `process_scheduling_input.txt` has:
```text
[process_id] [priority] [duration] [arrival_time]
