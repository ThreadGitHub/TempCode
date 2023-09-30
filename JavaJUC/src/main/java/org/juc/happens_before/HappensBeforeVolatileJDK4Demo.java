package org.juc.happens_before;

/**
 * 测试例子可能不正确
 * ---------这个测试测试的 1.4和1.3 和 1.8都有这个问题不知道为什么---------
*	JDK1.4 由于没有HappensBefore原则支持所以会出现 flag=true num=0的情况
*	C:\Users\Administrator\Desktop>java HappensBeforeVolatileDem
*	write: Thread-0
*	test: 1
*	read: Thread-1
*	test: 2
*	read: Thread-3
*	write: Thread-2
*	=====================num=99
*	=====================num=0
*	=====================flag=true
*	=====================flag=true
*/
public class HappensBeforeVolatileJDK4Demo{
	public static void main(String[] args) {
		for (int i = 1; i<= 5; i++) {
			Resource resource = new Resource();
			new ThreadA(resource).start();
			new ThreadB(resource).start();
			System.out.println("test: " + i);
		}
		
	}
}

class Resource{
	private int num = 0;
	private volatile boolean flag = false;
	
	public void write(){
		System.out.println("write: " + Thread.currentThread().getName());
		num = 99;
		flag = true;
	}
	
	public void read(){
		System.out.println("read: " + Thread.currentThread().getName());

		System.out.println("===================== "+ Thread.currentThread().getName() +" num=" + num);
		System.out.println("===================== "+ Thread.currentThread().getName() +" flag=" + flag);

		if(flag){
			System.out.println("===================="+ Thread.currentThread().getName() +" num= " + num);
		}
	}
}

class ThreadA extends Thread{
	private Resource obj;
	
	public ThreadA(Resource obj){
		this.obj = obj;
	}
	
	public void run(){
		obj.write();
	}
}

class ThreadB extends Thread{
	private Resource obj;
	
	public ThreadB(Resource obj){
		this.obj = obj;
	}
	
	public void run(){
		obj.read();
	}
}