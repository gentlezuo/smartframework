package org.smart4j.framework.example.service.impl;

import org.smart4j.framework.example.entity.User;
import org.smart4j.framework.example.service.UserService;
import org.smart4j.framework.orm.DataSet;
import org.smart4j.framework.tx.annotation.Service;
import org.smart4j.framework.tx.annotation.Transaction;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> findUserList() {
        return DataSet.selectList(User.class);
    }

    @Override
    public User findUser(long id) {
        return DataSet.select(User.class, "id = ?", id);
    }

    @Override
    @Transaction
    public boolean saveUser(Map<String, Object> fieldMap) {
        return DataSet.insert(User.class, fieldMap);
    }

    @Override
    @Transaction
    public boolean updateUser(long id, Map<String, Object> fieldMap) {
        return DataSet.update(User.class, fieldMap, "id = ?", id);
    }

    @Override
    @Transaction
    public boolean deleteUser(long id) {
        return DataSet.delete(User.class, "id = ?", id);
    }

    public static void main(String[] args) {
        UserServiceImpl service=new UserServiceImpl();
        List<User> list=service.findUserList();
        for (User user:list){
            System.out.println(user);
        }
    }
}
