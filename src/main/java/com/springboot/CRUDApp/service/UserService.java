package com.springboot.CRUDApp.service;

import com.springboot.CRUDApp.models.User;
import java.util.List;

public interface UserService {
    List<User> index();

    public User show(int id);

    public void save(User user);

    public void update(int id, User updatedUser);

    public void delete(int id);
}
