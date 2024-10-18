package org.yourcompany.yourproject;

public class User {
    private String name;

    private int age;

    private int dpt;

    public User(String name, int age, int dpt){
        this.name = name;
        this.age = age;
        this.dpt = dpt;
    }

    public int getDpt() {
        return dpt;
    }

    public void setDpt(int dpt) {
        this.dpt = dpt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
