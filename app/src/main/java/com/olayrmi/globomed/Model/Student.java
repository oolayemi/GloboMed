package com.olayrmi.globomed.Model;

public class Student {

    String name, surname, marks, id;

    public Student(String  id, String name, String surname, String marks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.marks = marks;
    }
    public Student(){}

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
