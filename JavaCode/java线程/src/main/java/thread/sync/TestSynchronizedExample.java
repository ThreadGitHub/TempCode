package thread.sync;

/**
 * 测试Synchronized使用场景
 */

import java.util.concurrent.TimeUnit;

/**
 * 1.普通方法 未加 synchronized
 * 输出结果： B - A
 * 未加锁按时间输出
 */
class ClassA {
    public void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public void b() {
        System.out.println("B");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassA classA = new ClassA();
        new Thread(()-> {
            try {
                classA.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(1);

        new Thread(()-> {
            classA.b();
        }).start();
    }
}


/**
 * 2.同一个对象 两个方法都加了 synchronized
 * 输出结果： A-B
 * 同时锁住了一个对象的实例所以先输出A后输出B
 */
class ClassB {
    public synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public synchronized void b() {
        System.out.println("B");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassB classB = new ClassB();
        new Thread(()-> {
            try {
                classB.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()-> {
            classB.b();
        }).start();
    }
}

/**
 * 3. 添加一个普通方法 看先打印 A 还是 hello
 * 输出结果： hello - A
 * 先输出无锁的时间短的hello 后输出 时间长的A
 */
class ClassC {
    public synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public synchronized void b() {
        System.out.println("B");
    }

    public void hello() {
        System.out.println("hello...");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassC classC = new ClassC();
        new Thread(()-> {
            try {
                classC.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()-> {
            classC.hello();
        }).start();
    }
}


/**
 * 4.两个个对象 两个方法都加了 synchronized
 * 输出结果： B-A
 * 因为是非静态的两个实例方法，所以是两把锁，按时间先输出 B 后输出A
 */
class ClassD {
    public synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public synchronized void b() {
        System.out.println("B");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassD classD = new ClassD();
        new Thread(()-> {
            try {
                classD.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        ClassD classD2 = new ClassD();
        new Thread(()-> {
            classD2.b();
        }).start();
    }
}


/**
 * 5. 同一个对象 两个静态方法都加了 synchronized
 * 输出结果： A-B
 * 锁的是类对象，先输出A后输出B
 */
class ClassE {
    public static synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public static synchronized void b() {
        System.out.println("B");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassE classE = new ClassE();
        new Thread(()-> {
            try {
                classE.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()-> {
            classE.b();
        }).start();
    }
}

/**
 * 6. 同两个对象 两个静态方法都加了 synchronized
 * 输出结果： A-B
 * 锁的是类对象，先输出A后输出B
 */
class ClassF{
    public static synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public static synchronized void b() {
        System.out.println("B");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassF classF = new ClassF();
        new Thread(()-> {
            try {
                classF.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        ClassF classF2 = new ClassF();
        new Thread(()-> {
            classF2.b();
        }).start();
    }
}

/**
 * 7. 同一个对象 有一个静态方法 一个普通方法 synchronized
 * 输出结果： B-A
 * 实例方法锁的是实例，静态方法锁的是类对象，两把锁所以是先输出时间快的B后输出A
 */
class ClassG{
    public static synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public synchronized void b() {
        System.out.println("B");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassG classG = new ClassG();
        new Thread(()-> {
            try {
                classG.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()-> {
            classG.b();
        }).start();
    }
}

/**
 * 8. 两个对象 有一个静态方法 一个普通方法 synchronized
 * 输出结果： B-A
 * 实例方法锁的是实例，静态方法锁的是类对象，两把锁所以是先输出时间快的B后输出A
 */
class ClassH{
    public static synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("A");
    }

    public synchronized void b() {
        System.out.println("B");
    }

    public static void main(String[] args) throws InterruptedException {
        ClassH classH = new ClassH();
        new Thread(()-> {
            try {
                classH.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        ClassH classH2 = new ClassH();
        new Thread(()-> {
            classH2.b();
        }).start();
    }
}

public class TestSynchronizedExample {
    public void test() throws Exception {
        synchronized (this) {
            System.out.println("aaa");
            throw new Exception();
        }
    }

    public static void main(String[] args) {

    }
}


