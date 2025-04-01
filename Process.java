public class Process {
  int id;
  int priority;
  int duration;
  int arrivalTime;
  int maxWaitingTime;
  int waitingTime;
  int remainingTime;
  Process(int id, int priority, int duration, int arrivalTime) {
    this.id = id;
    this.priority = priority;
    this.duration = duration;
    this.arrivalTime = arrivalTime;
    this.maxWaitingTime = ProcessScheduling.maxWaitingTime;
    waitingTime = 0;
    remainingTime = duration;
  }

  /**
   * This is the method which prints the basic information of the Process, including id, priority,
   * duration and arrival time.
   */
  void selfIntro() {
    System.out.printf("Id = %d, priority = %d, duration = %d, arrival time = %d\n", this.id,
        this.priority, this.duration, this.arrivalTime);
  }

  /**
   * This is the method which returns whether the process reaches the max waiting time, and update
   * the waiting time, and the max waiting time left.
   */
  boolean reachMaxWaitingTime() {
    this.maxWaitingTime -= 1;
    this.waitingTime += 1;
    return this.maxWaitingTime == 0;
  }

  /**
   * This is the method which updates the priority of the process when the process reaches the max
   * waiting time, prints the information about the updated priority, and resets the max waiting time left.
   */
  void updatePriorityWhenExceedMaxWaitingTime() {
    this.priority -= 1;
    System.out.printf("Process %d reached maximum wait time... decreasing priority to %d\n", this.id, this.priority);
    this.maxWaitingTime = ProcessScheduling.maxWaitingTime;
  }

  /**
   * This is the method which prints the information of the Process when the process is completed,
   * including id, duration, arrival time, job remaining time, and the current time.
   * @param currentTime
   */
  void ending(int currentTime) {
    System.out.printf("Finished running Process id = %d\n", this.id);
    System.out.printf("Arrival = %d\n", this.arrivalTime);
    System.out.printf("Duration = %d\n", this.duration);
    System.out.printf("Run time left = %d\n", this.remainingTime);
    System.out.printf(" at time %d\n", currentTime);
  }

  /**
   * This is the method which update the job remaining time, and prints the execution information,
   * including id, the current time and the job remaining time.
   * @param currentTime
   */
  void executeProcess(int currentTime) {
    this.remainingTime -= 1;
    System.out.printf("Executed process ID:%d, at time %d Remaining: %d\n", this.id, currentTime, this.remainingTime);
  }

  /**
   * This is the method which prints the information of the Process after each process execution,
   * including id, arrival time, duration, job remaining time, and the current time.
   * @param currentTime
   */
  void runningIntro(int currentTime) {
    System.out.printf("Now running Process id = %d\n", this.id);
    System.out.printf("Arrival = %d\n", this.arrivalTime);
    System.out.printf("Duration = %d\n", this.duration);
    System.out.printf("Run time left = %d\n", this.remainingTime);
    System.out.printf(" at time %d\n", currentTime);
  }
}
