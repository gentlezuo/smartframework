package org.smart4j.framework.example.service;

import org.smart4j.framework.example.entity.Book;
import org.smart4j.framework.example.entity.Student;
import org.smart4j.framework.ioc.annotation.Inject;
import org.smart4j.framework.tx.annotation.Service;

@Service
public class StudentReadBook {
    @Inject
    private Book book;
    @Inject
    private Student student;

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }
}
