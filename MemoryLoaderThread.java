import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class MemoryLoaderThread extends Thread {

  private BlockingQueue<PCB> jobQueue;
  private Queue<PCB> readyQueue;

  public MemoryLoaderThread(BlockingQueue<PCB> jobQueue, Queue<PCB> readyQueue){
    this.jobQueue = jobQueue;
    this.readyQueue = readyQueue;
  }

  public void run(){
    while(true){
      try{

        PCB job = jobQueue.take();
        synchronized(SchedulerQueues.class){
          if(job.memoryRequired + SchedulerQueues.used_memory <= SchedulerQueues.total_memory){
            SchedulerQueues.used_memory = SchedulerQueues.used_memory + job.memoryRequired; 
            job.state = "READY";
            readyQueue.add(job);
            System.out.println("Moved into ready queue" + job.id);
          } else{
            jobQueue.put(job);
            Thread.sleep(100);
          }
        }
       

      } catch(Exception e){
        e.printStackTrace();
      }
    }
  }

}
