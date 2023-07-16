package com.code.defaultInterface;

public interface DefaultInterface {
    default void test() {
        System.out.println("testMethod.");
    }

    static void testStatic() {
        System.out.println("testStaticMethod.");
    }
}