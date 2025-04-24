import java.util.Queue;

public class FCFS {
  

  public static void run( Queue<PCB> readyQueue ){

    int currentTime = 0;
    int count = 0;
    int total_Waiting_Time = 0;
    int AVG_WaitingTime = 0;
    int total_Turnaround_Time = 0;
    int AVG_turnaroundTime = 0;
    System.out.println("Gantt Chart: ");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+");
    System.out.println("|    Process ID    |     Start      |      End     | Turnaround Time   | Waiting Time   |");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+");
    

    while(!readyQueue.isEmpty()){
      PCB p = readyQueue.poll();
      
      count++;
      int Enter = currentTime;
      
      p.waitingTime = currentTime;
      total_Waiting_Time = total_Waiting_Time + p.waitingTime;
      
      currentTime = currentTime + p.burstTime;
      p.turtnaroundTime = p.burstTime + p.waitingTime;// same as TT=CT (all p arrive in 0)
      total_Turnaround_Time = total_Turnaround_Time + (p.turtnaroundTime );
      
      try {
        Thread.sleep(1000); // 1 second delay (adjust as needed)
        System.out.printf("| %16d | %14d | %12d | %17d | %14d |\n",p.id,Enter, p.turtnaroundTime,p.turtnaroundTime, p.waitingTime );
        System.out.println("+------------------+----------------+--------------+-------------------+----------------+");

       } catch (InterruptedException e) {
        e.printStackTrace();
        }
    }
    AVG_turnaroundTime = total_Turnaround_Time/count;
    AVG_WaitingTime = total_Waiting_Time/count; 
    System.out.println();
    System.out.println("The average waiting time is: " + AVG_WaitingTime);
    System.out.println("The average turnaround time is: " + AVG_turnaroundTime);


  }

}


