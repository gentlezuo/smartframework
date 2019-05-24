package org.smart4j.framework.example.dao;


import org.smart4j.framework.example.entity.Student;

import java.util.List;

public interface StudentDao {

    Student queryStudentByName(String name);


}
