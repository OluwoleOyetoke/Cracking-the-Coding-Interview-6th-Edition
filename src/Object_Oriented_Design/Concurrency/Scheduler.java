/*
 * Copyright (C) 2018 Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Object_Oriented_Design.Concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread pool scheduler for concurrent programming
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Scheduler implements IScheduler {

    private final int CORE_POOL_SIZE = 4;
    private final int MAX_POOL_SIZE = 6;
    private final int KEEP_ALIVE_TIME = 30; //in seconds
    private final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    //private final BlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(1);
    private final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();
    Semaphore semaphore = new Semaphore(60000); //maximum 60,000 concurent activities going on per time
    ReentrantLock lock = new ReentrantLock();
    private static Scheduler scheduler;

    private static ThreadPoolExecutor mThreadExecutor;

    private Scheduler() {
        //ExecutorService newCachedThreadPool = Executors.newCachedThreadPool(); //if we wish to use the executor service
        mThreadExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, WORK_QUEUE);
        mThreadExecutor.allowCoreThreadTimeOut(true); //apply keep alive time to core threads also
        mThreadExecutor.setRejectedExecutionHandler(new RejectionHandler());
    }

    @Override
    public void execute(ITask task) {

        Future<String> future = (Future) mThreadExecutor.submit(new Runnable() {
            @Override
            public void run() {
                boolean permit = false;
                try {
                    /*Even though we have a pool with a queue to queue up task, here, 
                    we are making sure not more than 60, 000 execution request are running per time
                    the rest should be dropped*/
                    permit = semaphore.tryAcquire(1, TIME_UNIT); 
                    System.out.println("Permit: " + permit + ". Remaining Semaphore Access: " + semaphore.availablePermits());
                    if (permit) {
                        Thread currentThread = Thread.currentThread();
                        try {
                            String threadName = currentThread.getName();
                            System.out.println(task.toString() + " Submitted to queue. Being executed by " + threadName);
                            Thread.sleep(3000);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        task.run();

                    } else {
                        System.out.println("Semphoare did not grant access");
                    }
                } catch (InterruptedException exception) {
                    System.out.println(exception);
                } finally {
                    if (permit) {
                        semaphore.release();
                    }
                }
            }
        }
        );
        //System.out.println(future.get());//this will be a blcking call

    }

    public static Scheduler getInstance() {
        if (mThreadExecutor == null) {
            scheduler = new Scheduler();
            return scheduler;
        } else {
            return scheduler;
        }
    }
}
