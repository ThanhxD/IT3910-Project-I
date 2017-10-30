package com.guide_operator.Cotroller;

import com.guide_operator.Entities.User;
import com.guide_operator.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class loginController {

    @Autowired
    private userService userService;
    @RequestMapping(value = "/login/{username}", method= RequestMethod.GET)
    public User getUserByUsername(@PathVariable("username") String  username){
        return userService.getUserByUsername(username);
    }

}
