/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */

public class Philosopher implements Runnable
{
    long sleepTime = (long) (Math.random() * 2000) + 1000; // random delay value between 1000 to 2999 ms
    private int idNum; // philosopher number
    private DiningServerImpl right, left; // right & left chopstick instances

    // constructor
    public Philosopher(int num, DiningServerImpl rightchop, DiningServerImpl leftchop)
    {
        this.idNum = num; // initialize philosopher number
        this.right = rightchop;
        this.left = leftchop; 
    }

    @Override
    public void run() {
        try {
            // alternate between thinking and eating
            while(true)
            {
                // check if the philosopher's num is even or odd
                if((idNum % 2) == 0)
                {
                    // pick up right fork 1st if idNum == even
                    System.out.println("Philosopher " + idNum + "is THINKING for " + sleepTime + " ms.");
                    Thread.sleep(sleepTime); // sleep for the generated delay
                    right.takeForks(idNum);
                    left.takeForks(idNum);
                    System.out.println("Philosopher " + idNum + "is EATING for " + sleepTime + " ms."); 
                    Thread.sleep(sleepTime); // sleep for the generated delay   
                    left.returnForks(idNum);
                    right.returnForks(idNum);
                }
                else
                {
                    // pick up left fork 1st if idNum == even
                    System.out.println("Philosopher " + idNum + "is THINKING for " + sleepTime + " ms.");
                    Thread.sleep(sleepTime); // sleep for the generated delay
                    left.takeForks(idNum);
                    right.takeForks(idNum);
                    System.out.println("Philosopher " + idNum + "is EATING for " + sleepTime + " ms."); 
                    Thread.sleep(sleepTime); // sleep for the generated delay   
                    right.returnForks(idNum);
                    left.returnForks(idNum);
                }
            }
        } catch (Exception e) {
            System.out.println("run caught"); 
            System.err.println(e);
        }
    }
}