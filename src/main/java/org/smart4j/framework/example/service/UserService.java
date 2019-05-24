package org.smart4j.framework.example.service;

import org.smart4j.framework.example.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findUserList();

    User findUser(long id);

    boolean saveUser(Map<String, Object> fieldMap);

    boolean updateUser(long id, Map<String, Object> fieldMap);

    boolean deleteUser(long id);
}