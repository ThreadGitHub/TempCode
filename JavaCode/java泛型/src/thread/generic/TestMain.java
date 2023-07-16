package thread.generic;

import org.junit.Test;

public class TestMain {
    @Test
    public void test() {
//        GenericClass<String> genericClass = new GenericClass("test");
//        String value = genericClass.getValue();
//        System.out.println(value);
//
//        GenericClass<Integer> genericClass2 = new GenericClass<>(123);
//        Integer value2 = genericClass2.getValue();
//        System.out.println(value2);
//
//        ObjectClass objectClass = new ObjectClass();
//        String classType = objectClass.getClass(String.class);
//        System.out.println(classType);

//        Box<Integer> box = new Box<>();
//        box.setFirst(100);
//        showBox(box);

        Box<String> box2 = new Box<>();
        box2.setFirst("字符串");
        showBox(box2);
    }

    public static <T> void showBox(Box<? extends String> box) {
        String first = box.getFirst();
        System.out.println(first);
    }
}
