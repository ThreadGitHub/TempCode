package org.juc.volatile_demo;

/**
 * this逸出问题
 * @author thread
 * @date 2023/10/13 12:08
 */
public class ThisEscapeDemo2 {
    final int a;
    int b=0;
    static ThisEscapeDemo2 obj;
    public ThisEscapeDemo2(){
        a=1;
        b=1;
        obj=this;
    }
    public static void main(String[] args) {
        /**
         * 线程A：模拟构造器中this逃逸,将未构造完全对象引用抛出
         */
        new Thread(() -> obj=new ThisEscapeDemo2()).start();
        /**
         * 线程B：读取对象引用，访问a/b变量
         */
        Thread threadB=new Thread(() -> {
            ThisEscapeDemo2 objA = obj;
            try {
                System.out.println(objA.b);
            }catch (NullPointerException e){
                System.out.println("发生空指针错误：普通变量b未被初始化");
            }
            try {
                System.out.println(objA.a);
            } catch (NullPointerException e) {
                System.out.println("发生空指针错误：final变量a未被初始化");
            }
        });
        threadB.start();
    }
}
