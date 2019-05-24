package org.smart4j.framework.example.entity;

import org.smart4j.framework.ioc.annotation.Bean;
import org.smart4j.framework.ioc.annotation.Inject;
import org.smart4j.framework.orm.annotation.Entity;

@Bean
public class Student {

    public String name;
    public int age;
    public String  school;
    public boolean gender;
    @Inject
    public Book book;

    public Student() {
    }

    public Student(String name, int age, String school, boolean gender, Book book) {
        this.name = name;
        this.age = age;
        this.school = school;
        this.gender = gender;
        this.book = book;
    }

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                ", gender=" + gender +
                ", book=" + book.getName() +
                '}';
    }
}
