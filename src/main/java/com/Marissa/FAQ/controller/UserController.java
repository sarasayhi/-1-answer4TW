package com.Marissa.FAQ.controller;

import com.Marissa.FAQ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过spring data jpa 调用方法 api :localhost:8099/users/byname?username=xxx
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/byname", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getUser(HttpServletRequest request) {
//        Map<String, Object> map = CommonUtil.getParameterMap(request);
//        String username = (String) map.get("username");
        return new ResponseEntity<>(userService.getUserByName("test"), HttpStatus.OK);
    }
}
