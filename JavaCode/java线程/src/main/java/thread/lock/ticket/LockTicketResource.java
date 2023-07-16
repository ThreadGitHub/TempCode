package thread.lock.ticket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 卖票资源类
 */
public class LockTicketResource {
    private int size;

    private ReentrantLock reentrantLock = new ReentrantLock();

    public LockTicketResource(int size) {
        this.size = size;
    }

   public void sale() {
       //上锁
       reentrantLock.lock();
       try {
           if (size > 0) {
               System.out.println(Thread.currentThread().getName() + " 卖出：[" + size-- + "]号票, 剩余：" + size);
               // 睡眠一段时间来显示出超卖的现象
               try {
                   TimeUnit.MILLISECONDS.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       } finally {
           //解锁
           reentrantLock.unlock();
       }
   }
}
