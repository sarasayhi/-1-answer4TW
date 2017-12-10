package com.Marissa.FAQ.controller;

import com.Marissa.FAQ.repository.po.User;
import com.Marissa.FAQ.repository.userRepository;
import com.Marissa.FAQ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private userRepository Repository;

    @RequestMapping(value = "/byname", method = RequestMethod.GET)
    @ResponseBody
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
