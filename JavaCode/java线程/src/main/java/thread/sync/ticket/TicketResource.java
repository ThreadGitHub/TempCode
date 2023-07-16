package thread.sync.ticket;

import java.util.concurrent.TimeUnit;

/**
 * 卖票资源类
 */
public class TicketResource {
    private int size;

    public TicketResource(int size) {
        this.size = size;
    }

   public synchronized void sale() {
        if (size > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出：[" + size-- + "]号票, 剩余：" + size);
            // 睡眠一段时间来显示出超卖的现象
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
   }
}
