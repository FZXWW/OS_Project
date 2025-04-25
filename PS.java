import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PS {

  public static void run(Queue<PCB> readyQueue){

    List<PCB> queue = new ArrayList<>(readyQueue);

    for(int i=0 ; i < queue.size() ; i++){
      for(int j=0 ; j < queue.size()-i-1 ; j++){
        PCB p1 = queue.get(j);
        PCB p2 = queue.get(j+1);

        if(p1.priority < p2.priority){
          PCB temp = p1;
          queue.set(j , p2);
          queue.set(j+1 , temp);
        }
      }
    }

    int currentTime = 0;
    int total_Turnaround_Time = 0;
    int total_Waiting_Time = 0;
    int count = 0;
    int end ;
    boolean isStarved;

    System.out.println("Gantt Chart: ");
        System.out.println("+------------------+----------------+--------------+-------------------+----------------+--------- +");
        System.out.println("|    Process ID    |     Start      |      End     | Turnaround Time   | Waiting Time   |  Starved |");
        System.out.println("+------------------+----------------+--------------+-------------------+----------------+----------+");

    for (int i=0 ; i < queue.size() ; i++){
      PCB p = queue.get(i);

      int start = currentTime;
      p.waitingTime = currentTime;
      currentTime += p.burstTime;
      end = currentTime;

      p.turtnaroundTime = p.burstTime + p.waitingTime;
      total_Turnaround_Time += p.turtnaroundTime;
      total_Waiting_Time += p.waitingTime;
      count++;

      if(p.waitingTime > p.priority){
        isStarved = true;
      }else{ isStarved = false; }

      try {
        Thread.sleep(500); // تأخير لمحاكاة المعالجة
        
        System.out.print("| " + String.format("%16d", p.id) + " | ");
        System.out.print(String.format("%14d", start) + " | ");
        System.out.print(String.format("%12d", end) + " | ");
        System.out.print(String.format("%17d", p.turtnaroundTime) + " | ");
        System.out.print(String.format("%14d", p.waitingTime) + " | ");
        System.out.print(String.format("%8s", (isStarved)) + " |\n");
        
        System.out.println("+------------------+----------------+--------------+-------------------+----------------+----------+");
        
      } catch (InterruptedException e) {
        System.out.println("حدث خطأ في التأخير");
         }
    }
    System.out.println();
    System.out.println("The average waiting time is: " + total_Waiting_Time/count);
    System.out.println("The average turnaround is: " + total_Turnaround_Time/count);
    }

  }
