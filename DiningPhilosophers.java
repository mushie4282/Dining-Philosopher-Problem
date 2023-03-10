/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 */

public class DiningPhilosophers
{
  // number of philosopher
  static final int numOfPhilo = 5;

  public static void main(String args[])
  {
    DiningServerImpl[] chopsticks = new DiningServerImpl[numOfPhilo]; // array of chopsticks that is shared amongst the philosophers
    Thread[] threads = new Thread[numOfPhilo]; // array of threads
    
    // Initialize reentrant locks within chopstick array
    for(int i = 0; i < numOfPhilo; i++)
    {
      chopsticks[i] = new DiningServerImpl(); 
    }

    // Initialize & start threads
    for(int i = 0; i < numOfPhilo; i++)
    {
      Philosopher person = new Philosopher(i, chopsticks[(i + 1) % numOfPhilo], chopsticks[i]);
      threads[i] = new Thread(person); // each thread will execute a philosopher instance
      threads[i].start(); 
    }
  }
}