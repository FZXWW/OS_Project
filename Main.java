
import java.util.*;

public class Main {

  public static void main(String[] args) {



    Scanner input = new Scanner(System.in);


    boolean choice = true;

    System.out.println("------------------------------------");
    System.out.print("Enter The Path Of The File : ");
    System.out.println();


    String file = input.next();



    do {

    System.out.println("------------------------------------");
    System.out.println("Choice from the following Algorthims");
    System.out.println("1-First Come First Serve (FCFS)");
    System.out.println("2-Round Robin (RR)");
    System.out.println("3-Priority scheduling (PS)");
    System.out.println("4- Start Read");
    System.out.println("5-Stop");
    System.out.println("------------------------------------");

    System.out.println("Enter your choice : ");
    int inputt = input.nextInt();

    if(inputt==1)
        FCFS.run(SchedulerQueues.readyQueue);
    else if(inputt==2)
        RR.run(SchedulerQueues.readyQueue);
    else if(inputt==3)
        PS.run(SchedulerQueues.readyQueue);
    else if(inputt==4) {
        new FileReaderThread(SchedulerQueues.jobQueue,file).start();
        new MemoryLoaderThread(SchedulerQueues.jobQueue, SchedulerQueues.readyQueue).start();
    }
    else if(inputt==5) {
        System.out.println("Goodbye!!");
        choice = false;
    }
    else
        System.out.println("Invild Input");



    }while(choice);
    

  }

}