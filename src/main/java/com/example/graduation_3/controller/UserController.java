package com.example.graduation_3.controller;

import com.example.graduation_3.dto.UserDTO;
import com.example.graduation_3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cy
 * @data 2020/4/30 - 16:47
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/addUser")
    public int addUser(@RequestBody UserDTO user){
        return userService.addUser(user);
    }
}
