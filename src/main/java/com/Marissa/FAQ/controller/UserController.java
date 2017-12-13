package com.Marissa.FAQ.controller;

import com.Marissa.FAQ.repository.po.User;
import com.Marissa.FAQ.repository.UserRepository;
import com.Marissa.FAQ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository Repository;

    @RequestMapping(value = "/byname", method = RequestMethod.GET)
    public String getUser() {
//        Map<String, Object> map = CommonUtil.getParameterMap(request);
//        String username = (String) map.get("username");
//        userService.getUserByName();
        User u= new User("1","33");
        Repository.save(u);
        return "success";
//        return new ResponseEntity<>(userService.getUserByName("test"), HttpStatus.OK);
    }
}