package thread.test;

import thread.annotation.MyAnnotation;
import thread.domain.Student;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        //通过反射去获得自定义注解配置
        Class stuClass = student.getClass();

        Method test = stuClass.getMethod("test", null);
            if(test.isAnnotationPresent(MyAnnotation.class)){
                MyAnnotation myAnnotation = test.getAnnotation(MyAnnotation.class);
                System.out.println("method : " + myAnnotation.values());
                stuClass.getMethod("setNames", List.class).invoke(student,Arrays.asList(myAnnotation.values()));
            }

            if(stuClass.isAnnotationPresent(MyAnnotation.class)){
                MyAnnotation annotation = (MyAnnotation)stuClass.getAnnotation(MyAnnotation.class);
                stuClass.getMethod("setName", String.class).invoke(student,annotation.value());
        }

        System.out.println(student);

    }

}
