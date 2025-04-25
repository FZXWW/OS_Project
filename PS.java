import java.util.*;

public class PS {

  public static void run(Queue<PCB> readyQueue) {

    
    PriorityQueue<PCB> priorityQueue = new PriorityQueue<>(new Comparator<PCB>() {
      @Override
      public int compare(PCB p1, PCB p2) {
       
        return Integer.compare(p1.priority, p2.priority); 
      }
    });
    priorityQueue.addAll(readyQueue);

    int degreeOfMultiProgramming = readyQueue.size();
    int numOfExcuted = 0;

    int currentTime = 0;
    float total_Turnaround_Time = 0;
    float total_Waiting_Time = 0;
    int count = 0;
    int end;
    boolean isStarved;

    System.out.println("Gantt Chart: ");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+--------- +");
    System.out.println("|    Process ID    |     Start      |      End     | Turnaround Time   | Waiting Time   |  Starved |");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+----------+");

    
    while (!priorityQueue.isEmpty()) {
      PCB p = priorityQueue.poll(); 

      int start = currentTime;
      p.waitingTime = currentTime;
      currentTime += p.burstTime;
      end = currentTime;
      numOfExcuted++;
      

      p.turtnaroundTime = p.burstTime + p.waitingTime;
      total_Turnaround_Time += p.turtnaroundTime;
      total_Waiting_Time += p.waitingTime;
      count++;

      isStarved = (numOfExcuted - 1 ) > (degreeOfMultiProgramming -1);

      try {
        Thread.sleep(500);
        
        System.out.print("| " + String.format("%16d", p.id) + " | ");
        System.out.print(String.format("%14d", start) + " | ");
        System.out.print(String.format("%12d", end) + " | ");
        System.out.print(String.format("%17d", p.turtnaroundTime) + " | ");
        System.out.print(String.format("%14d", p.waitingTime) + " | ");
        System.out.print(String.format("%8s", (isStarved)) + " |\n");

        System.out.println("+------------------+----------------+--------------+-------------------+----------------+----------+");

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      synchronized (SchedulerQueues.class) {
        SchedulerQueues.used_memory -= p.memoryRequired;
      }
    }

    System.out.println();
    System.out.println("The average waiting time is: " + total_Waiting_Time / count);
    System.out.println("The average turnaround is: " + total_Turnaround_Time / count);
  }

}
