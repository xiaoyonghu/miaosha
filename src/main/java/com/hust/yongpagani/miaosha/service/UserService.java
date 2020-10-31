package com.hust.yongpagani.miaosha.service;

import com.hust.yongpagani.miaosha.dao.UserMapper;
import com.hust.yongpagani.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Created by Divo
 * @date 2020/10/30
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public Boolean tx() {

        //如果不加事务的话，执行成功不会回滚
        User u2 = new User();
        u2.setId(3);
        u2.setName("Ducatti");
        userDao.insertUser(u2);

        // 执行不成功
        User u1 = new User();
        u1.setId(2);
        u1.setName("Apollo IE");
        userDao.insertUser(u1);

        return true;
    }
}
