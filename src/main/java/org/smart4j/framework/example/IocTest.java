package org.smart4j.framework.example;

import org.smart4j.framework.HelperLoader;
import org.smart4j.framework.example.entity.Book;
import org.smart4j.framework.example.entity.Student;
import org.smart4j.framework.example.service.StudentReadBook;
import org.smart4j.framework.ioc.BeanHelper;

public class IocTest {


    public static void main(String[] args) {
        HelperLoader.init();
        Student s=BeanHelper.getBean(Student.class);
        System.out.println(s.getBook().id);
        Book book=BeanHelper.getBean(Book.class);
        System.out.println(book.getStudent().age);
        //赋值
        s.setAge(99);
        book.setId(99999999L);
        System.out.println(s.toString());
        System.out.println(book.toString());
        System.out.println(s.getBook().id);
        System.out.println(book.getStudent().age);
        StudentReadBook studentReadBook= BeanHelper.getBean(StudentReadBook.class);
        System.out.println(studentReadBook.getBook());


    }
}
