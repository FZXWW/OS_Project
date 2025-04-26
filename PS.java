import java.util.*;

public class PS {

  public static void run(Queue<PCB> readyQueue) {

    PriorityQueue<PCB> priorityQueue = new PriorityQueue<>(new Comparator<PCB>() {
      public int compare(PCB p1, PCB p2) {
        return Integer.compare(p1.priority, p2.priority);
      }
    });

    int currentTime = 0;
    double total_Turnaround_Time = 0;
    double total_Waiting_Time = 0;
    int count = 0;
    int end;
    boolean isStarved;
    int numOfExcute = 0;
    int degreeOfMultiProgramming = readyQueue.size() - 1;

    int alreadyAdded = 0;

    System.out.println("Table: ");
    System.out.println("+------------------+----------+----------------+--------------+-------------------+----------------+----------+");
    System.out.println("|    Process ID    | Priority |     Start      |      End     | Turnaround Time   | Waiting Time   |  Starved |");
    System.out.println("+------------------+----------+----------------+--------------+-------------------+----------------+----------+");

    while (true) {
      
      synchronized (SchedulerQueues.class) {
        while (alreadyAdded < readyQueue.size()) {
          List<PCB> list = new ArrayList<>(readyQueue);
          priorityQueue.add(list.get(alreadyAdded));
          alreadyAdded++;
        }
      }

      if (priorityQueue.isEmpty()) {
        
        if (alreadyAdded == MemoryLoaderThread.countPinReadyQueue) {
          break; 
        } else {
          try {
            Thread.sleep(200); 
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          continue;
        }
      }

      PCB p = priorityQueue.poll();
      int start = currentTime;
      p.waitingTime = currentTime;
      currentTime += p.burstTime;
      end = currentTime;
      numOfExcute++;

      p.turtnaroundTime = p.burstTime + p.waitingTime;
      total_Turnaround_Time += p.turtnaroundTime;
      total_Waiting_Time += p.waitingTime;
      count++;

      
      isStarved = (numOfExcute - 1 ) > degreeOfMultiProgramming; 

      try {
        Thread.sleep(500);

        System.out.print("| " + String.format("%16d", p.id) + " | ");
        System.out.print(String.format("%8d", p.priority) + " | ");
        System.out.print(String.format("%14d", start) + " | ");
        System.out.print(String.format("%12d", end) + " | ");
        System.out.print(String.format("%17d", p.turtnaroundTime) + " | ");
        System.out.print(String.format("%14d", p.waitingTime) + " | ");
        System.out.print(String.format("%8s", isStarved ? "Yes" : "No") + " |\n");

        System.out.println("+------------------+----------+----------------+--------------+-------------------+----------------+----------+");

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      synchronized(SchedulerQueues.class){
        SchedulerQueues.used_memory -= p.memoryRequired;
      }
      try{
        Thread.sleep(1000);
      }catch( Exception e){
        e.printStackTrace();
      }
    }
    if( count != 0 ){
      System.out.println();
      System.out.println("The average waiting time is: " + total_Waiting_Time / count);
      System.out.println("The average turnaround is: " + total_Turnaround_Time / count);
    }
   
  }
}
