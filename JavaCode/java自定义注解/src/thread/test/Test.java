package thread.test;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String[] str = {"a","b","c","d","e"};
        List strList = Arrays.asList(str);
        System.out.println(strList.toString());
        //Arrays.asList转的List之后，再add元素就会报异常
        strList.add("f");
    }

}
