package com.example.jetpack.my.kotlin;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Student student = new Student("tom", 18);
        SimpleData<Student> simpleData = new SimpleData<>(student);
        handleData(simpleData);

        List<? extends People> peoples = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        peoples = students;

//        List<? extends People> peoples = new ArrayList<>();
//        peoples.add(new Student("tom", 18)); //无法编译，类型转换问题

        List<? super People> list = new ArrayList<>();
        list.add(new Student("tom", 18));

        People people = new People("Jack", 38);
        SimpleData<People> simpleData2 = new SimpleData<>(people);
        handleData2(simpleData2);

    }

    public static void handleData2(SimpleData<? super Student> data) {

    }

    public static void handleData(SimpleData<? extends People> data) {

    }

    public static class People {
        String name;
        int age;

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static class Student extends People {

        public Student(String name, int age) {
            super(name, age);
        }
    }

    public static class SimpleData<T> {

        public T data;

        public SimpleData(T data) {
            this.data = data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}
