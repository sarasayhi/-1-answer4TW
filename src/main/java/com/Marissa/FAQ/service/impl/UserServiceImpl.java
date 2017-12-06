package com.Marissa.FAQ.service.impl;

import com.Marissa.FAQ.repository.po.user;
import com.Marissa.FAQ.repository.userRepository;
import com.Marissa.FAQ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private userRepository userJpaDao;

    @Override
    public user getUserByName(String username) {
        user u= new user("1","33",new Date());
        return userJpaDao.save(u);
    }
}
