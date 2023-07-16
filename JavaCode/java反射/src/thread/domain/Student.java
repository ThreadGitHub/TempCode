package thread.domain;

public class Student {
    public Student(){
        System.out.println("无参构造！");
    }

    public Student(String name){
        this.name = name;
        System.out.println("我的名字：" + name);
    }

    private Student(String name,int age){
        this.name = name;
        this.age = age;
        System.out.println("名字：" + name + "\t" + "年龄：" + age);
    }

    public void sayHi(){
        System.out.println(this.name + " 打招呼！");
    }

    public String sayHi(String name){
        System.out.println(name + " 打招呼！");
        return name;
    }

    private void sayHi_private(){
        System.out.println("私有打招呼！");
    }

    public static void sayHi_static(){
        System.out.println("静态打招呼！");
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
