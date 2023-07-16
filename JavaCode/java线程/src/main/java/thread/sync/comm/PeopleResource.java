package thread.sync.comm;

/**
 * 一个大怨种资源
 */
public class PeopleResource {
    /**
     * 0-空闲, 1-干活
     */
    private int state;

    /**
     * 干活
     */
    public synchronized void doPeole() {
        // 判断不应该用 if 而应该用 while 否则会出现虚假唤醒问题
        // 因为wait在哪里睡就在哪里继续执行
        while (state != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        state++;

        System.out.println(Thread.currentThread().getName() + " 大怨种开始干活!  ---" + state);

        // 做完了唤醒其他大怨种
        this.notifyAll();
    }

    /**
     * 睡大觉
     */
    public synchronized void sleepPoeple() {
        // 判断不应该用 if 而应该用 while 否则会出现虚假唤醒问题
        // 因为wait在哪里睡就在哪里继续执行
        while (state != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        state--;

        System.out.println(Thread.currentThread().getName() + " 大怨种睡大觉! ---" + state);

        // 做完了唤醒其他大怨种
        this.notifyAll();
    }
}
