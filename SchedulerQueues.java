import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SchedulerQueues {

  public static BlockingQueue<PCB> jobQueue = new LinkedBlockingQueue<>();
  public static Queue<PCB> readyQueue = new LinkedList<>();
  public int total_memory = 2048;
  public int used_memory = 0;

}
