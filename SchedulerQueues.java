import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SchedulerQueues {

  public static BlockingQueue<PCB> jobQueue = new LinkedBlockingQueue<>();
  public static Queue<PCB> readyQueue = new LinkedList<>();
  public static int total_memory = 2048;
  public static int used_memory = 0;

}
