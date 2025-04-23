import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

public class FileReaderThread extends Thread{

  private BlockingQueue<PCB> jobQueue;

  public FileReaderThread(BlockingQueue<PCB> jobQueue){
  this.jobQueue = jobQueue;
}

public void run(){
  try (BufferedReader br = new BufferedReader(new FileReader("test.txt.txt"))){
    String line;
    while( (line = br.readLine()) != null ){
      String[] parts = line.split("[:;]");
      int id = Integer.parseInt(parts[0]);
      int busrt = Integer.parseInt(parts[1]);
      int priority = Integer.parseInt(parts[2]);
      int memory = Integer.parseInt(parts[3]);
      PCB process = new PCB (id, busrt, priority, memory);
      jobQueue.put(process);
      System.out.println("Loaded job: " + id);
    }
  } catch(Exception e){
    e.printStackTrace();
  }
  
}

}
