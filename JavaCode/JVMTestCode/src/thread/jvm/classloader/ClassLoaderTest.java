package thread.jvm.classloader;

import sun.net.spi.nameservice.dns.DNSNameService;

import java.io.IOException;

public class ClassLoaderTest {
    public static void main(String[] args) throws IOException {
        //获取应用类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //BootstrapClassLoad 引导类加载器 通过C++实现所以获取不到 为 null
        //String 为 jre/lib 下的核心API
        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);

        //AppClassLoader 应用类加载器 获取当前类的类加载器
        ClassLoader classLoader2 = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader2);

        //ExtClassLoader 扩展类加载器
        ClassLoader extClassLoader = classLoader2.getParent();
        System.out.println(extClassLoader);

        //jre/ext下的扩展类
        ClassLoader classLoader3 = DNSNameService.class.getClassLoader();
        System.out.println(classLoader3);


    }
}
