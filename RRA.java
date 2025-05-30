import java.util.LinkedList;
import java.util.Queue;

public class RRA {

  public static void run(Queue<PCB> readyQueue){
    
    int Quantum = 7;
    int currentTime = 0;
    int totalWaitingTime = 0;
    int totalTurnaroundTime = 0;
    int Count = 0;
    int end = 0 ;
    Queue<PCB> queue = new LinkedList<>(readyQueue);


    System.out.println("Gantt Chart:");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+");
    System.out.println("|    Process ID    |     Start      |      End     | Turnaround Time   | Waiting Time   |");
    System.out.println("+------------------+----------------+--------------+-------------------+----------------+");

    while(!queue.isEmpty()){
      PCB p = queue.poll();

      int start = currentTime;

      if(p.remainingTime == p.burstTime){
        p.waitingTime = currentTime;
        Count++;
      }

      int excTime = Math.min(Quantum, p.remainingTime);
      currentTime += excTime;
      p.remainingTime -= excTime;

       
       
        p.turtnaroundTime = currentTime;
        totalTurnaroundTime += currentTime;
        totalWaitingTime += p.waitingTime;

        if(p.remainingTime > 0 ){
          queue.add(p);
        }
       else if( p.remainingTime <= 0){
        end = p.turtnaroundTime - p.burstTime;
       }


        System.out.printf(
          "| %16d | %14d | %12d | %17d | %14d |\n",
          p.id,
          start,
          currentTime,
          p.turtnaroundTime,
          end
      );
      System.out.println("+------------------+----------------+--------------+-------------------+----------------+");
       
      

      try{
        Thread.sleep(500);
      }catch(InterruptedException e){
        e.printStackTrace();
      }

    }

    System.out.println();
    System.out.println("The average waiting timeis: " + (totalWaitingTime/Count));
    System.out.println("The average turnaround time is: " + (totalTurnaroundTime/Count));

  }

}
