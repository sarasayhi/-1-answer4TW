package com.Marissa.FAQ.service;

import com.Marissa.FAQ.repository.po.user;

public interface UserService {
    public user getUserByName(String username);
}
