package org.smart4j.framework.example.dao;

import org.smart4j.framework.example.entity.Book;

import java.util.List;

public interface BookDao {

    Book queryBookById(long id);

}
