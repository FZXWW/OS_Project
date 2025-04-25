import java.util.Queue;
import java.util.LinkedList;

public class RR {

private final static long quantum = 7; // The quantum Number 7

    public static void run( Queue<PCB> readyQueue ){

        Queue<PCB> temp_queue = new LinkedList<PCB>(readyQueue); // Temp Queue to don't modify the original queue

        long  time = 0; // Start Time

        int Waiting_Time = 0; // Waiting_Time For The Proocces To Find The Avg
        int TurnAround = 0; // TurnAround For The Proocces To Find The Avg
        int number_of_proocce = 0; // To Count The Number Of Prooces to find the Avg

        System.out.println();

        while(!(temp_queue.isEmpty())) { // To Loop To Procces all the Processes

         PCB p = temp_queue.poll(); // Pull The Procces whose turn it is


         long actualQuantum; // Intailize The Variable to take the min between quantum or process remainingTime
         if(quantum < p.remainingTime)
             actualQuantum = quantum;
         else
             actualQuantum = p.remainingTime;

         long start = time; // The Procces Start Time
         long end = start + actualQuantum; // To Calalute The Procces End Time
         time = end; // To Change The Time

         while(time < end);
         p.remainingTime -= actualQuantum; // Calalute The Proccce remainingTime


            if(p.remainingTime > 0) {
                temp_queue.add(p); // Add The Procce again if it not finish
System.out.println(" | " + start + " P" + p.id + " " + end + " | " );
                }
            else {

                number_of_proocce++; // Count The Proocce Number
                int Wait = (int)(end - p.burstTime); // Calalute The Waiting Time For Each Procce
                Waiting_Time += Wait; // Add The Waiting Time For Each Procce To Find The Avg
                TurnAround += end;

                System.out.println(" | " + start + " P" + p.id + " " + end + " (TurnAround: " + end + ") (WaitingTime: " + Wait + " ) | ");
                synchronized(SchedulerQueues.class){
                    SchedulerQueues.used_memory -= p.memoryRequired;
                  }
        }

    }
    if(number_of_proocce !=0 ) {
    System.out.println("\n The Avg WaitingTime is : "+Waiting_Time/number_of_proocce);
    System.out.println(" The Avg TurnAround is : "+TurnAround/number_of_proocce);}
}
}
