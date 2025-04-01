import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import net.datastructures.Entry;
import net.datastructures.HeapAdaptablePriorityQueue;
import java.util.List;

public class ProcessScheduling {
  static int maxWaitingTime = 30;

  /**
   * This is the method which runs the simulation of the whole process scheduling.
   */
  public void runSimulation() {
    FileReader f = new FileReader("src/process_scheduling_input.txt");
    Map<Integer, Process> data = new HashMap<>();
    try {
      data = f.loadData();
    } catch(Exception e){
      System.out.println(e);
    }

    Comparator<Process> arrivalTimeComparator = (a, b) -> a.arrivalTime - b.arrivalTime;
    HeapAdaptablePriorityQueue<Process, Integer> processPq = new HeapAdaptablePriorityQueue(arrivalTimeComparator);
    for (Process p : data.values()) {
      p.selfIntro();
      processPq.insert(p, p.arrivalTime);
    }

    Comparator<Process> processComparator = (a, b) -> a.priority - b.priority;
    HeapAdaptablePriorityQueue<Process, Integer> executionPq = new HeapAdaptablePriorityQueue(processComparator);

    System.out.printf("\nMaximum wait time = %d\n\n", maxWaitingTime);
    int currentTime = 0;
    Integer lastProcessId = null;
    while (!processPq.isEmpty() || !executionPq.isEmpty()) {
      if (!processPq.isEmpty()) {
        Entry<Process, Integer> earliest = processPq.min();
        Process p = earliest.getKey();
        if (p.arrivalTime <= currentTime) {
          processPq.remove(earliest);
          executionPq.insert(p, p.priority);
        }
      }
      Integer executionProcessId = null;
      if (!executionPq.isEmpty()) {
        Entry<Process, Integer> firstPriority = executionPq.min();
        Process p = firstPriority.getKey();
        executionProcessId = p.id;
        if (lastProcessId == null || lastProcessId != p.id) {
          p.runningIntro(currentTime);
          lastProcessId = p.id;
        }
        p.executeProcess(currentTime);
        if (p.remainingTime == 0) {
          executionPq.remove(firstPriority);
          p.ending(currentTime);
        }
      }
      if (!executionPq.isEmpty()) {
        List<Entry<Process, Integer>> temp = new ArrayList<>();
        Iterator iter = executionPq.iterator();
        while (iter.hasNext()) {
          Entry<Process, Integer> entry = (Entry<Process, Integer>) iter.next();
          Process p = entry.getKey();
          if (p.id != executionProcessId) {
            if (p.reachMaxWaitingTime()) {
              temp.add(entry);
            }
          }
        }
        for (Entry<Process, Integer> entry : temp) {
          executionPq.remove(entry);
          Process p = entry.getKey();
          p.updatePriorityWhenExceedMaxWaitingTime();
          executionPq.insert(p, p.priority);
        }
      }
      currentTime++;
    }
    System.out.printf("Finished running all processes at time %d\n", currentTime - 1);
    double avg = 0.0;
    for (Process p : data.values()) {
      avg += p.waitingTime * 1.0 / data.size();
    }
    System.out.printf("Average wait time: %.2f\n", avg);
  }
  public static void main(String[] args) {
    ProcessScheduling pS = new ProcessScheduling();
    pS.runSimulation();
  }
}