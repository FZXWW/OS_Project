public class Main {

  public static void main(String[] args) {
    new FileReaderThread(SchedulerQueues.jobQueue).start();
    new MemoryLoaderThread(SchedulerQueues.jobQueue, SchedulerQueues.readyQueue).start();


  try{
    Thread.sleep(4000);
  } catch(InterruptedException e){
    e.printStackTrace();
  }

  RR.run(SchedulerQueues.readyQueue);


  }

}
