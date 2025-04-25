import java.util.LinkedList;
import java.util.Queue;

public class FCFS {
  

  public static void run( Queue<PCB> readyQueue ){

  

    int currentTime = 0;
    int count = 0;
    int total_Waiting_Time = 0;
    int AVG_WaitingTime = 0;
    int total_Turnaround_Time = 0;
    int AVG_turnaroundTime = 0;
    System.out.println("Table: ");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+");
    System.out.println("|    Process ID    |     Start      |      End     | Turnaround Time   | Waiting Time   |");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+");
    

    while(!readyQueue.isEmpty()){
      PCB p = readyQueue.poll();
      
      count++;
      int Enter = currentTime;
      
      p.waitingTime = currentTime;
      total_Waiting_Time = total_Waiting_Time + p.waitingTime;
      
      currentTime += p.burstTime;
      p.turtnaroundTime = p.burstTime + p.waitingTime;// same as TT=CT (all p arrive in 0)
      total_Turnaround_Time +=  (p.turtnaroundTime );

      
      
      try {
        Thread.sleep(500); 
        System.out.printf("| %16d | %14d | %12d | %17d | %14d |\n",p.id,Enter, p.turtnaroundTime,p.turtnaroundTime, p.waitingTime );
        System.out.println("+------------------+----------------+--------------+-------------------+----------------+");

       } catch (InterruptedException e) {
        e.printStackTrace();
        }

        synchronized(SchedulerQueues.class){
          SchedulerQueues.used_memory -= p.memoryRequired;
        }
    }
    
    if(count != 0 ){
      System.out.println();
      System.out.println("The average waiting time is: " +total_Waiting_Time/count);
      System.out.println("The average turnaround time is: " + total_Turnaround_Time/count);
  
    }
   

  }

}


