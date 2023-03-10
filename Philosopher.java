/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */

public class Philosopher implements Runnable
{
    private int idNum; // philosopher number
    private DiningServerImpl right, left; // right & left chopstick instances

    // constructor
    public Philosopher(int num, DiningServerImpl rightchop, DiningServerImpl leftchop)
    {
        this.idNum = num; // initialize philosopher number
        this.right = rightchop; // intialize right side chopstick
        this.left = leftchop; // intialize left side chopstick
    }

    /**
     * @brief Thread's execution code. Each thread will alternate between "THINKING" and "EATING".
     */
    @Override
    public void run() {        
        try {
            while(true)
            {
                long sleepTime = (long) (Math.random() * 2000) + 1000; // thread delay variable
                System.out.println("Philosopher " + idNum + " is THINKING for " + sleepTime + " ms.");
                Thread.sleep(sleepTime); // thread becomes dormant until 'sleepTime' has elasped

                // acquire both chopsticks
                boolean rightFlag = right.takeForks();
                boolean leftFlag = left.takeForks();

                // eat only if both chopsticks are acquired
                if(rightFlag && leftFlag)
                {
                    System.out.println("Philosopher " + idNum + " has the chopsticks");
                    System.out.println("Philosopher " + idNum + " is EATING for " + sleepTime + " ms.");  
                    Thread.sleep(sleepTime); // thread becomes dormant until 'sleepTime' has elasped
                }
                
                // release both chopsticks
                left.returnForks();
                right.returnForks();
            }
        } catch (InterruptedException inter) {
            System.err.println("Philosopher " + idNum + "gave " + inter.getMessage());
        }
        catch (Exception e){
            System.err.println("Philosopher " + idNum + "gave " + e.getMessage());
        }
    }
}