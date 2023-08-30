package com.m22tup.test.controller;

import com.m22tup.test.service.UserService;
import com.m22tup.test.service.m22tupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String setReserve(Model model){

        userService.setPlace("내이름", "01020002000", 8,1L);

        return "성공";
    }


}
