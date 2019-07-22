package com.budgettracker.hibernate.services;

import com.budgettracker.api.models.UserCredentials;
import com.budgettracker.hibernate.dao.UserDao;
import com.budgettracker.hibernate.entity.User;
import com.budgettracker.hibernate.security.Encrypto;

public class UserService {

    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User save(UserCredentials uc){
        try {
            int id = userDao.save(new User(uc.getEmail(), Encrypto.encrypt(uc.getPassword())));
            return getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User get(UserCredentials uc) {
        User user = userDao.getByEmail(uc.getEmail());
        try {
            if(Encrypto.decrypt(user.getPassword()).equals(uc.getPassword()))
                return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
