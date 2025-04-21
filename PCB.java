public class PCB {

  public int id;
  public int burstTime;
  public int priority;
  public int memoryRequired;
  public int remainingTime; //RR
  public int waitingTime = 0;
  public int turtnaroundTime = 0;
  public String state = "NEW";

  public PCB(int id, int br, int pr, int mr){
    this.id = id;
    burstTime = br;
    priority = pr;
    memoryRequired = mr;
    remainingTime = br;
  }

}
