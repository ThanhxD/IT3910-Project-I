package com.guide_operator.Cotroller;

import com.guide_operator.Entities.Guide;
import com.guide_operator.Entities.User;
import com.guide_operator.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class showUserController {
    @Autowired
    private userService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id")int id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void insertGuidetoBb(@RequestBody Guide guide){
        userService.insertGuidetoDb(guide);
    }

}
