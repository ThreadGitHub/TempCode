package thread.jvm;

class StaticClass {
    static String str = "......";

    static {
        System.out.println("我加载了好兄弟!");
    }
}

public class ClinitTest {
    private static int num = 10;

    static {
        num = 99;
    }

    public static void main(String[] args) {
        //如果没有使用到不好导致类的初始化
        System.out.println("程序启动StaticClass没有被初始化");

        //使用类的静态资源会导致类的初始化 调用 clinit()
        System.out.println(StaticClass.str);
    }
}
