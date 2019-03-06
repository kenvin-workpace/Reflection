package com.whz.reflection.module;

import android.util.Log;

/**
 * Created by kevin on 2018/5/9
 */
public class Person {

    private final String aTag = Person.class.getSimpleName();

    private String name;

    @Deprecated
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        Log.e(aTag, "getName:" + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
        Log.e(aTag, "setName:" + name);
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public void setEat(String... foods) {
        for (String food : foods) {
            Log.e(aTag, "setEat:" + food);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
