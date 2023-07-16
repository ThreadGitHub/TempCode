package thread.annotation;

import java.lang.annotation.*;

//声明注解使用范围 为 类，接口，枚举
@Target({ElementType.TYPE,ElementType.METHOD})
//声明注解存在的生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    //定义注解属性
    String value() default "";

    String[] values() default {};
}
