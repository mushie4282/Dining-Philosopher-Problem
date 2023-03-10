/**
 * DiningServer.java
 *
 * This class contains the methods called by the philosophers.
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class DiningServerImpl implements DiningServer 
{
	ReentrantLock stick = new ReentrantLock(); 

	public void takeForks(int philNumber)
	{
		try {
			if(stick.tryLock())
			{
				// Acquires the lock only if it is not held by another thread at the time of invocation.
				stick.lock();
			}
			else
			{
				// Causes the current thread to wait until it is awakened, typically by being notified or interrupted.
				stick.wait(); 
			}
		} catch (Exception e) {
			System.out.println("takeFork caught"); 
			System.err.println(e.getMessage()); 
		}
	}

	public void returnForks(int philNumber)
	{
		try {
			stick.unlock();
			
		} catch (Exception e) {
			System.out.println("returnForks caught"); 
			System.err.println(e.getMessage()); 
		}
	}

}
