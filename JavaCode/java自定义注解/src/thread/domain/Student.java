package thread.domain;

import thread.annotation.MyAnnotation;

import java.util.ArrayList;
import java.util.List;

@MyAnnotation("test")
public class Student {
    List names = new ArrayList();

    public List getNames() {
        return names;
    }

    public void setNames(List names) {
        this.names.addAll(names);
    }

    public void setName(String name){
        names.add(name);
    }

    @MyAnnotation(values = {"test1","test2"})
    public void test(){

    }

    @Override
    public String toString() {
        return "Student{" +
                "names=" + names +
                '}';
    }
}
