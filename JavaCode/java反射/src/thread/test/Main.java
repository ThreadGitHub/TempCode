package thread.test;

import org.junit.Test;
import thread.domain.Student;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {
    /**
     * java三种方式获取反射后的 Class对象
     * 1.Object.getClass()
     * 2.任何类型的都有一个静态的class属性
     * 3.Class.forName
     */
    @Test
    public void test() throws ClassNotFoundException {
        Student student = new Student();
        Class studentClass = student.getClass();
        System.out.println(studentClass);

        Student student1 = new Student();
        Class student1Class = student1.getClass();

        Class studentClass2 = Student.class;
        System.out.println(studentClass2);

        Class studentClass3 = Class.forName("thread.domain.Student");
        System.out.println(studentClass3);

        System.out.println(studentClass == student1Class);
        System.out.println(studentClass == studentClass2);
        System.out.println(studentClass2 == studentClass3);

    }

    /**
     * 获取Class类中的构造方法和一些方法和属性
     */
    @Test
    public void test2() throws Exception{
        Class studentClass = null;
        try {
            studentClass = Class.forName("thread.domain.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 反射构造方法调用
         */
        //调用无参构造
        Constructor constructor = studentClass.getConstructor(null);
        Student student = (Student) constructor.newInstance(null);
        System.out.println(student);

        //调用有参构造
        Constructor constructorArgName = studentClass.getConstructor(String.class);
        Student student2 = (Student) constructorArgName.newInstance("小明");
        System.out.println(student2);

        //调用私有构造创建对象
        Constructor declaredConstructor = studentClass.getDeclaredConstructor(String.class, int.class);
        //强制访问私有构造方法
        declaredConstructor.setAccessible(true);
        Student student3 = (Student) declaredConstructor.newInstance("私有",20);
        System.out.println(student3);

        /**
         * 调用方法
         */
        Method sayHi = studentClass.getMethod("sayHi");
        sayHi.invoke(student);

        Method sayHi_arg = studentClass.getMethod("sayHi", String.class);
        sayHi_arg.invoke(student,"小贺");

        Method sayHi_private = studentClass.getDeclaredMethod("sayHi_private");
        sayHi_private.setAccessible(true);
        sayHi_private.invoke(student);

        Method sayHi_static = studentClass.getMethod("sayHi_static");
        sayHi_static.invoke(student);
    }

}
