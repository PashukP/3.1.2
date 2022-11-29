package com.springboot.CRUDApp.service;

import com.springboot.CRUDApp.dao.UserDAO;
import com.springboot.CRUDApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> index() {
        return userDAO.index();
    }

    @Transactional(readOnly = true)
    @Override
    public User show(int id) {
        return userDAO.show(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void update(int id, User updatedUser) {
        userDAO.update(id, updatedUser);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }
}
