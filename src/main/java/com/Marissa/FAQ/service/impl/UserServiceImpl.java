package com.Marissa.FAQ.service.impl;

import com.Marissa.FAQ.repository.po.User;
import com.Marissa.FAQ.repository.userRepository;
import com.Marissa.FAQ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private userRepository userJpaDao;

    @Override
    public void getUserByName() {
        User u= new User("1","33",new Date());
        userJpaDao.save(u);
    }
}
