import java.util.Queue;

public class FCFS {

  public static void run( Queue<PCB> readyQueue ){

    int currentTime = 0;
    System.out.println("Gantt Chart: ");

    while(!readyQueue.isEmpty()){
      PCB p = readyQueue.poll();
      p.waitingTime = currentTime;
      currentTime = currentTime + p.burstTime;
      p.turtnaroundTime = currentTime;
      System.out.println("| P" + p.id + " ");
    }
    System.out.println("|");

  }

}
