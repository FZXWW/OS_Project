import java.util.Queue;

public class FCFS {

  public static void run( Queue<PCB> readyQueue ){

    int currentTime = 0;
    System.out.println("Gantt Chart: ");

    while(!readyQueue.isEmpty()){
      PCB p = readyQueue.poll();
      int Enter = currentTime;
      p.waitingTime = currentTime;
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
