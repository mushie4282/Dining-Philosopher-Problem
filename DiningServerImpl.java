/**
 * DiningServer.java
 *
 * This class contains the methods called by the philosophers.
 *
 */
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl implements DiningServer 
{
	ReentrantLock stick = new ReentrantLock();

	/**
	 * @brief Return TRUE if reentrant lock is acquired. Return FALSE if reentrant lock is occupied.
	 * // Acquires the lock only if it is not held by another thread at the time of invocation.
	 */
	public boolean takeForks()
	{
		try {
			// check if lock is available
			if(stick.tryLock())
			{
				// lock acquired
				return true; 
			}
			else
			{
				// lock is held by another thread
				return false; 
			}
		} 
		catch(IllegalMonitorStateException e) 
		{
			System.err.println("takeFork(): " + e.getMessage()); // previous error message: current thread not owner
		}
		return false; 
	}

	/**
	 * @brief The lock is released only if the current thread is holding a lock. 
	 * Nothing will happen if the thread is not holding one.
	 */
	public void returnForks()
	{
		try {
			// check if the current thread is holding a lock
			if(stick.isHeldByCurrentThread())
			{
				// release the lock
				stick.unlock();	
			}
		} 
		catch (IllegalMonitorStateException e) 
		{
			System.err.println("returnFork(): " + e.getMessage()); // previous error message: null
		}
	}

}
