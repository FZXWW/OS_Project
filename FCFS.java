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

    while(!readyQueue.isEmpty()){
      PCB p = readyQueue.poll();
      
      count++;
      int Enter = currentTime;
      p.waitingTime = currentTime;
      
      total_Waiting_Time = total_Waiting_Time + p.waitingTime;
      AVG_WaitingTime = total_Waiting_Time/count; 
      total_Turnaround_Time = total_Turnaround_Time + currentTime;
      AVG_turnaroundTime = total_Turnaround_Time/count;

      currentTime = currentTime + p.burstTime;
      p.turtnaroundTime = currentTime;
      try {
        Thread.sleep(1000); // 1 second delay (adjust as needed)
        System.out.println("      P" + p.id + "      |");
        System.out.print(Enter +"          " +p.turtnaroundTime);

    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
    System.out.println();

  }

}
