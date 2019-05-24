package org.smart4j.framework.example.entity;

import org.smart4j.framework.ioc.annotation.Bean;
import org.smart4j.framework.ioc.annotation.Inject;
@Bean
public class Book {

    public String name;
    public long id;
    @Inject
    public Student student;

    public Book(){

    }

    public Book(String name, long id, Student student) {
        this.name = name;
        this.id = id;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", student=" + student.getName() +
                '}';
    }
}
